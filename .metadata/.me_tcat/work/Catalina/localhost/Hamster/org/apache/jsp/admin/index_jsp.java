package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\t<title>Full Layout - jQuery EasyUI Demo</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/demo.css\">\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/easyUi/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/admin/index.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tfunction showcontent(oper){\r\n");
      out.write("\t\t\tif(oper=='toAddArticle' || oper=='toArticleList'){\r\n");
      out.write("\t\t\t\t$('#mainFrame').attr('src','admin/article?operation=' + oper); \r\n");
      out.write("\t\t\t}else if(oper =='toCategoryList'){\r\n");
      out.write("\t\t\t\t$('#mainFrame').attr('src','admin/category?operation=' + oper); \r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\t<div data-options=\"region:'north',border:false\" style=\"height:70px;background:#B3DFDA;padding:0px;\">\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t<div style=\"width: 50%;float: left;\">\r\n");
      out.write("\t\t\t<b><i><font size=\"10\" color=\"white\">Tale</font><font size=\"5\" color=\"green\">博客管理后台&nbsp&nbspV1.0</font></i></b>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t    <div style=\"width: 40%;float: right;margin-top: 35px;\">\r\n");
      out.write("\t    \t<button onclick=\"editPassword()\" style=\"float: right;\">修改密码</button>\r\n");
      out.write("\t    \t<form action=\"admin/login\" method=\"post\" style=\"float: right;\">\r\n");
      out.write("\t\t\t\t登录用户：<font size=\"3\" color=\"green\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"operation\" class=\"operation\" value=\"logout\">\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"退出登录\">\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'west',split:true,title:'菜单管理'\" style=\"width:200px;padding:10px;\">\r\n");
      out.write("\t\t<div class=\"easyui-accordion\" data-options=\"fit:true,border:false\">\r\n");
      out.write("\t\t\t<div title=\"文章管理\" data-options=\"selected:true\" style=\"padding:10px;\">\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" onclick=\"showcontent('toAddArticle')\" class=\"easyui-linkbutton\" style=\"width:100%\" data-options=\"iconCls:'icon-large-picture',size:'large',iconAlign:'top'\">发布文章</a>\r\n");
      out.write("\t\t\t\t<hr/>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" onclick=\"showcontent('toArticleList')\" class=\"easyui-linkbutton\" style=\"width:100%\" data-options=\"iconCls:'icon-large-shapes',size:'large',iconAlign:'top'\">文章列表</a>\r\n");
      out.write("\t\t\t\t<hr/>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" onclick=\"showcontent('toCategoryList')\" class=\"easyui-linkbutton\" style=\"width:100%\" data-options=\"iconCls:'icon-large-clipart',size:'large',iconAlign:'top'\">文章分类</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"音乐管理\" style=\"padding:10px;\">\r\n");
      out.write("\t\t\t\tcontent2\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div title=\"友链管理\" style=\"padding:10px\">\r\n");
      out.write("\t\t\t\tcontent3\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'east',split:true,collapsed:false,title:'操作信息'\" style=\"width:200px;padding:10px;\">admin登陆了</div>\r\n");
      out.write("\t<div data-options=\"region:'south',border:false\" style=\"height:50px;background:#A9FACD;padding:10px;\">友链：<a href=\"#\">百度</a></div>\r\n");
      out.write("\t<div id=\"content\" data-options=\"region:'center',title:'工作台'\">\r\n");
      out.write("\t\t <IFRAME name=\"mainFrame\" src=\"\"  height=\"100%\" width=\"100%\" frameBorder=0 scrolling=\"auto\" src=\"\" id=\"mainFrame\"></IFRAME>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"dlg\" class=\"easyui-dialog\" title=\"修改密码\" style=\"width:300px;height:250px;padding:10px\"\r\n");
      out.write("\t\t\tdata-options=\"\r\n");
      out.write("\t\t\t\tbuttons: [{\r\n");
      out.write("\t\t\t\t\ttext:'Ok',\r\n");
      out.write("\t\t\t\t\ticonCls:'icon-ok',\r\n");
      out.write("\t\t\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t\t\tsaveUser();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},{\r\n");
      out.write("\t\t\t\t\ttext:'Cancel',\r\n");
      out.write("\t\t\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t\t\talert('cancel');;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}]\r\n");
      out.write("\t\t\t\">\r\n");
      out.write("\t\t\t<form id=\"fm\" method=\"post\">\r\n");
      out.write("\t\t\t\t<div style=\"margin-bottom:20px\">\r\n");
      out.write("\t\t\t\t\t<label for=\"username1\" class=\"label-left\">用户名:&nbsp&nbsp&nbsp</label>\r\n");
      out.write("\t\t\t\t\t<input id=\"username\" name=\"username\" class=\"easyui-validatebox tb\" data-options=\"required:true,validType:'length[3,10]'\" \r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"margin-bottom:20px\">\r\n");
      out.write("\t\t\t\t\t<label for=\"password\" class=\"label-left\">新密码:&nbsp&nbsp&nbsp</label>\r\n");
      out.write("\t\t\t\t\t<input id=\"password\" name=\"password\" class=\"easyui-validatebox tb\" data-options=\"required:true,validType:'length[3,10]'\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"margin-bottom:20px\">\r\n");
      out.write("\t\t\t\t\t<label for=\"confirmPassword\" class=\"label-left\">确认密码:</label>\r\n");
      out.write("\t\t\t\t\t<input id=\"confirmPassword\"  name=\"confirmPassword\" class=\"easyui-validatebox tb\" data-options=\"required:true,validType:'length[3,10]'\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"operation\" value=\"changePassword\">\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
