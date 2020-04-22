package org.apache.ibatis.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.Configuration;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 刷新使用进程
 *
 * @author gongtao
 * @date 2019-04-09
 */
public class RefreshRunnable implements java.lang.Runnable {

    public static Logger log = Logger.getLogger(RefreshRunnable.class);

    private Resource[] mapperLocations;
    private Configuration configuration;

    /**
     * 上一次刷新时间
     */
    private Long beforeTime = 0L;
    /**
     * 是否执行刷新
     */
    private static boolean refresh = false;
    /**
     * 延迟刷新秒数
     */
    private static int delaySeconds;
    /**
     * 休眠时间
     */
    private static int sleepSeconds;
    /**
     * 是否启用
     */
    private static boolean enabled;

    /**
     * 是否关闭
     */
    private static boolean closed = false;

    /** 线程工厂 */
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("mybatis-xml-reload-%d").build();
    /** 定时扫描线程池 */
    private static final ScheduledThreadPoolExecutor THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1, THREAD_FACTORY);

    static {
        delaySeconds = PropertiesUtil.getInt("delaySeconds");
        sleepSeconds = PropertiesUtil.getInt("sleepSeconds");
        enabled = "true".equals(PropertiesUtil.getString("enabled"));

        delaySeconds = delaySeconds == 0 ? 50 : delaySeconds;
        sleepSeconds = sleepSeconds == 0 ? 1 : sleepSeconds;

        log.debug("[delaySeconds] " + delaySeconds);
        log.debug("[sleepSeconds] " + sleepSeconds);

    }

    public static boolean isRefresh() {
        return refresh;
    }

    public RefreshRunnable(Resource[] mapperLocations, Configuration configuration) {
        this.mapperLocations = mapperLocations;
        this.configuration = configuration;
    }

    @Override
    public void run() {

        beforeTime = System.currentTimeMillis();
        if (enabled) {
            start(this);
        }
    }

    public void start(final RefreshRunnable runnable) {
        refresh = true;
        System.out.println("========= Enabled refresh mybatis xml =========");
        THREAD_POOL_EXECUTOR.scheduleAtFixedRate(() -> {
            try {
                runnable.refresh(mapperLocations, beforeTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, delaySeconds, sleepSeconds, TimeUnit.SECONDS);
    }

    /**
     * 关闭热加载线程
     */
    public static void closeReloadThread(){
        log.info("Shutting down mybatis-xml-reload scheduled job");
        if (enabled && refresh && !closed){
            THREAD_POOL_EXECUTOR.shutdown();
            closed = true;
        }
    }

    /**
     * 执行刷新
     *
     * @param mapperLocations 刷新目录
     * @param beforeTime      上次刷新时间
     * @throws NestedIOException     解析异常
     * @throws FileNotFoundException 文件未找到
     */
    public void refresh(Resource[] mapperLocations, Long beforeTime) throws Exception {

        // 本次刷新时间
        Long refreshTime = System.currentTimeMillis();

        List<File> refreshList = this.getRefreshFile(mapperLocations,
                beforeTime);
        if (refreshList.size() > 0) {
            log.debug("refresh files:" + refreshList.size());
        }
        for (File file : refreshList) {
            System.out.println("Refresh file: " + file.getAbsolutePath());
            log.debug("refresh file:" + file.getAbsolutePath());
            log.debug("refresh filename:" + file.getName());

            // 清理已加载的资源标识，方便让它重新加载。
            Field loadedResourcesField = configuration.getClass().getDeclaredField("loadedResources");
            loadedResourcesField.setAccessible(true);
            Set loadedResourcesSet = ((Set) loadedResourcesField.get(configuration));
            loadedResourcesSet.remove(file.getAbsolutePath());

            SqlSessionFactoryBean.refresh(new FileInputStream(file),
                    file.getAbsolutePath(), configuration);
        }
        // 如果刷新了文件，则修改刷新时间，否则不修改
        if (refreshList.size() > 0) {
            this.beforeTime = refreshTime;
        }
    }

    /**
     * 获取需要刷新的文件列表
     *
     * @param mapperLocations 目录
     * @param beforeTime      上次刷新时间
     * @return 刷新文件列表
     */
    private List<File> getRefreshFile(Resource[] mapperLocations, Long beforeTime) {
        List<File> refreshList = new ArrayList<>();
        for (Resource mapperLocation : mapperLocations) {
            try {
                if (mapperLocation instanceof FileSystemResource) {
                    File file = mapperLocation.getFile();
                    if (this.check(file, beforeTime)) {
                        refreshList.add(file);
                    }
                }
            } catch (IOException e) {
                log.error("get file error", e);
            }
        }

        return refreshList;
    }

    /**
     * 判断文件是否需要刷新
     *
     * @param file       文件
     * @param beforeTime 上次刷新时间
     * @return 需要刷新返回true，否则返回false
     */
    public boolean check(File file, Long beforeTime) {
        if (file.lastModified() > beforeTime) {
            return true;
        }
        return false;
    }


}
