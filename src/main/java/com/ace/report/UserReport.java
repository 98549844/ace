package com.ace.report;

import com.lowagie.text.pdf.BaseFont;
import com.ace.config.ReportConfig;
import com.util.OsUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @ClassName: Reports
 * @Description: 打印动态pdf报表
 * @author: Administrator
 * @date: 2016年12月30日 下午8:01:09
 */

/**
 * @Classname: Report
 * @Date: 16/7/2021 4:43 下午
 * @Author: garlam
 * @Description:
 */

//https://dynamicreports.org/
//flying-saucer-pdf html转pdf
public class UserReport {
    private static final Logger log = LogManager.getLogger(UserReport.class.getName());
    private final ReportConfig reportConfig;

    private final static String port = "3310";
    private static final String url = "jdbc:log4jdbc:mysql://localhost:" + port + "/ace?characterEncoding=UTF-8&useUnicode=true&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";

    // get application datasource to receive data
    public UserReport(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //main method
        buildReport(getConn(), null);

        //get application properties value to receive data
        //UserReport userReport = new UserReport(new ReportConfig());
        //userReport.buildReport(userReport.getConnection(), null);
    }

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        // return (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/ace?characterEncoding=UTF-8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8", "root", "garlamau");
        return DriverManager.getConnection(url, "root", "root");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(reportConfig.getForName());
        return DriverManager.getConnection(reportConfig.getUrl(), reportConfig.getUserName(), reportConfig.getPassword());
    }

    @SuppressWarnings("deprecation")
    public static void buildReport(Connection conn, String sqlString) throws SQLException {
        JasperReportBuilder report = DynamicReports.report();//创建空报表
        //设置报表的一系列样式 ，stl是创建和自定义风格的一组方法
        StyleBuilder boldStl = DynamicReports.stl.style().bold();
        StyleBuilder boldCenteredStl = DynamicReports.stl.style(boldStl).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder titleStl = DynamicReports.stl.style(boldCenteredStl).setFontSize(16);
        StyleBuilder columnTitleStl = DynamicReports.stl.style(boldCenteredStl).setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);//设置列名栏的背景颜色为灰色
        StyleBuilder fontStyleBuilder = DynamicReports.stl.style().setPadding(2).setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        columnTitleStl.setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        titleStl.setPdfFontName("STSong-Light").setPdfEncoding("UniGB-UCS2-H").setPdfEmbedded(BaseFont.NOT_EMBEDDED);

        report.setPageFormat(PageType.A5); //设置每一页的格式

        report.columns(Columns.column("操作日期", "createdDate", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("用户姓名", "username", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("ip", "ip", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("主机", "hostName", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("用户ID", "userId", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER), Columns.column("email", "email", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER)).setColumnStyle(fontStyleBuilder)   //查询的数据的字体格式
                .setColumnTitleStyle(columnTitleStl) //设置列名的风格
                .setHighlightDetailEvenRows(true)  //偶数行高亮显示
                .title(Components.text("用户列表").setStyle(titleStl))//标题
                .pageFooter(Components.pageXofY().setStyle(boldCenteredStl))//页角
                .setDataSource("SELECT * FROM users", conn);
        //.setDataSource("SELECT * FROM ReportMessage WHERE OperateTimeCustomerID = '" + sqlString + "'", conn)
        //数据源
        try {
            //显示报表, main方法会挂起,不会close, 关闭预览窗口后不退出程序
            report.show(false);
            FileOutputStream fileOutputStream = null;
            if (OsUtil.getOsName().contains(OsUtil.LINUX)) {
                fileOutputStream = new FileOutputStream("/Users/garlam/IdeaProjects/ace/src/main/resources/files/" + System.currentTimeMillis() + ".pdf");//构建一个pdf存放的输出位置
            } else if (OsUtil.getOsName().contains(OsUtil.MAC)) {
                fileOutputStream = new FileOutputStream("/Users/garlam/IdeaProjects/ace/src/main/resources/files/" + System.currentTimeMillis() + ".pdf");//构建一个pdf存放的输出位置
            } else if (OsUtil.getOsName().contains(OsUtil.WINDOWS)) {
                fileOutputStream = new FileOutputStream("C:\\ideaPorject\\ace\\src\\main\\resources\\files\\" + System.currentTimeMillis() + ".pdf");//构建一个pdf存放的输出位置
            }

            report.toPdf(fileOutputStream);//打印的pdf地址
            fileOutputStream.flush();  //保证pdf输出完毕
            fileOutputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

