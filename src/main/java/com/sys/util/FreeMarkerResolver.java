package com.sys.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



/**
 * 解析freemarker的工具
 * @author lism
 *
 */
public class FreeMarkerResolver {

    private static final Logger log = Logger.getLogger(FreeMarkerResolver.class);

    private static Configuration freeMarkerConfigurer = null;
    
    public static String templatePath = "";

    private static Configuration getFreemarkerConfigurer() throws IOException {
           if (freeMarkerConfigurer == null) {
               log.info("初始化FreeMarker引擎");
               freeMarkerConfigurer = new Configuration();
                // 设置系统内部模板路径
               log.info("设置FreeMarker系统内部模板路径 -->"+ templatePath);
                freeMarkerConfigurer.setDirectoryForTemplateLoading(new File(templatePath));
                freeMarkerConfigurer.setDefaultEncoding("UTF-8");
           }
           return freeMarkerConfigurer;
           }

    /**
     * 获取模板
     * @param templatePath 相对于Common.SYS_ROOT_DIR 的路径名
     * @return
     * @throws IOException
     */
    private static Template getTemplate(String templatePath) throws IOException {
        return getFreemarkerConfigurer().getTemplate(templatePath);
    }
    

    /**
     * 解析模板，得到html文本<br>
     * 异常时返回 null
     * @param templatePath 模板路径
     * @param model 填充字符
     * @return 文本
     */
    public static String htmlText(String templatePath, Map<String, String> model) {
        String htmltext = null;
        Template template = null;
            try {
                template = getTemplate(templatePath);
                htmltext = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            } catch (IOException e) {
            	log.error("模板没找到 --> " + templatePath + File.separator + templatePath);
                e.printStackTrace();
            } catch (TemplateException e) {
                log.error("模板没找到 --> " + templatePath + File.separator + templatePath);
                e.printStackTrace();
            }

        return htmltext;
    }
}
