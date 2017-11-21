package org.apache.jsp.admin.articleManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class categoryList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>添加文章分类</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/demo.css\">\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t#fm{\r\n");
      out.write("\t\t\tmargin:0;\r\n");
      out.write("\t\t\tpadding:10px 30px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.ftitle{\r\n");
      out.write("\t\t\tfont-size:14px;\r\n");
      out.write("\t\t\tfont-weight:bold;\r\n");
      out.write("\t\t\tcolor:#666;\r\n");
      out.write("\t\t\tpadding:5px 0;\r\n");
      out.write("\t\t\tmargin-bottom:10px;\r\n");
      out.write("\t\t\tborder-bottom:1px solid #ccc;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.fitem{\r\n");
      out.write("\t\t\tmargin-bottom:5px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.fitem label{\r\n");
      out.write("\t\t\tdisplay:inline-block;\r\n");
      out.write("\t\t\twidth:80px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/easyUi/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/admin/articleManager/categoryList.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t<!--\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("    \t<h2>文章分类列表管理</h2>\r\n");
      out.write("\t<div style=\"margin:20px 0;\"></div>\r\n");
      out.write("\t\r\n");
      out.write("\t<table id=\"dg\" title=\"分类列表\" class=\"easyui-datagrid\" style=\"width:100%\"\r\n");
      out.write("\t\t\turl=\"admin/category?operation=categoryList\"\r\n");
      out.write("\t\t\ttoolbar=\"#toolbar\"\r\n");
      out.write("\t\t\trownumbers=\"true\" fitColumns=\"true\" singleSelect=\"true\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th field=\"code\" width=\"50\">分类代码</th>\r\n");
      out.write("\t\t\t\t<th field=\"name\" width=\"50\">分类名称</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"toolbar\">\r\n");
      out.write("\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"newCategory()\">添加</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"editCategory()\">编辑</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"removeCategory()\">删除</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"dlg\" class=\"easyui-dialog\" style=\"width:350px;height:200px;padding:10px 20px\" closed=\"true\" buttons=\"#dlg-buttons\">\r\n");
      out.write("\t\t<div class=\"ftitle\">分类信息</div>\r\n");
      out.write("\t\t<form id=\"fm\" method=\"post\">\r\n");
      out.write("\t\t\t<div class=\"fitem\">\r\n");
      out.write("\t\t\t\t<label>分类代码:</label>\r\n");
      out.write("\t\t\t\t<input name=\"code\" class=\"easyui-validatebox\" required=\"true\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"fitem\" >\r\n");
      out.write("\t\t\t\t<label>分类名称:</label>\r\n");
      out.write("\t\t\t\t<input name=\"name\" class=\"easyui-validatebox\" required=\"true\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"dlg-buttons\">\r\n");
      out.write("\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\" onclick=\"saveCategory()\">保存</a>\r\n");
      out.write("\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" onclick=\"javascript:$('#dlg').dialog('close')\">取消</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
