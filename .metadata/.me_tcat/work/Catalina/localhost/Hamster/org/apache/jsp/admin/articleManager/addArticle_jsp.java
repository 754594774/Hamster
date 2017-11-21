package org.apache.jsp.admin.articleManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class addArticle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>添加文章</title>\r\n");
      out.write(" \t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/themes/color.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/easyUi/demo.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/easyUi/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/editor/themes/default/default.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/editor/plugins/code/prettify.css\" />\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"js/editor/kindeditor-all-min.js\"></script>\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"js/editor/lang/zh-CN.js\"></script>\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"js/editor/plugins/code/prettify.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/admin/articleManager/addArticle.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<!-- \r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("\t<form action=\"\" method=\"post\" id=\"articleForm\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t    <input type=\"hidden\" name=\"operation\" value=\"addArticle\">\r\n");
      out.write("\t\t<div class=\"easyui-panel\" style=\"width:100%;height:160px;padding:5px;\">\r\n");
      out.write("\t\t\t<div style=\"width:80%;float: left;\">\r\n");
      out.write("\t\t\t\t<div style=\"padding:10px;\">\r\n");
      out.write("\t\t\t\t\t\t<select id=\"categoryId\" class=\"easyui-combobox\" name=\"categoryId\" label=\"文章分类:\" style=\"width:45%;\" required=\"true\">\r\n");
      out.write("\t\t\t\t\t\t  \t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t<input id=\"title\" name=\"title\"  label=\"标题:\" class=\"easyui-textbox\" style=\"width:45%;\" required=\"true\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"padding:10px;\">\r\n");
      out.write("\t\t\t\t\t<input id=\"intro\" name=\"intro\"  label=\"简介:\" class=\"easyui-textbox\" style=\"width: 90%\" required=\"true\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div  style=\"padding:10px;\">\r\n");
      out.write("\t\t\t\t\t<input id=\"introPic\" name =\"introPic\" label=\"简介图片:\" class=\"easyui-filebox\" data-options=\"onChange:function(){preview(this)},prompt:'选择简介图片...',buttonText:'&nbsp;选&nbsp;择&nbsp;'\" style=\"width:45%;\" required=\"true\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"easyui-panel\" style=\"width:15%;height: 140px;float: right;\">\r\n");
      out.write("\t\t\t\t<img id=\"previewPic\" alt=\"预览\" src=\"\" style=\"width: 100%;height: 98%;\" /> \r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t<div style=\"margin: 10px 0\">\r\n");
      out.write("\t\t\t\t<textarea name=\"content\" style=\"margin-top: 10px; width: 100%; height: 500px;\" required=\"true\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</textarea>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div style=\"margin: 10px 0;padding:10px 0;\">\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton c7\" style=\"width: 100px; float: right\" onclick=\"addDraft()\">存为草稿</a>\r\n");
      out.write("\t\t\t<label style=\"width: 5px; float: right\">\r\n");
      out.write("\t\t\t\t&nbsp\r\n");
      out.write("\t\t\t</label>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton c6\" style=\"width: 100px; float: right\" onclick=\"addArticle()\">发布文章</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</body>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /admin/articleManager/addArticle.jsp(39,9) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/admin/articleManager/addArticle.jsp(39,9) '${categorys}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${categorys}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /admin/articleManager/addArticle.jsp(39,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("category");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t                \t<option value=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${category.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${category.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t                ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
