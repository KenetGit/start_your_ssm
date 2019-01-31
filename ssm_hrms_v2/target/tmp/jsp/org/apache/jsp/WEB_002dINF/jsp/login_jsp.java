/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.1.v20140609
 * Generated at: 2019-01-31 15:44:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>登录页面</title>\r\n");
      out.write("    <!-- Bootstrap Core CSS -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css\">\r\n");
      out.write("\r\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("    <script src=\"//cdn.bootcss.com/jquery/2.1.4/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <script src=\"http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-4 col-md-offset-4\" style=\"margin: 380px 380px\">\r\n");
      out.write("            <div class=\"login-panel panel panel-default\" >\r\n");
      out.write("                <div class=\"panel-heading\">\r\n");
      out.write("                    <h3 class=\"panel-title\" style=\"text-align: center;\">登录</h3>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"panel-body\">\r\n");
      out.write("                    <form role=\"form\" action=\"#\" method=\"post\" id=\"login_form\">\r\n");
      out.write("                        <fieldset>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <input class=\"form-control\" placeholder=\"用户名:admin\" name=\"username\" autofocus>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <input class=\"form-control\" placeholder=\"密码:1234\" name=\"password\" type=\"password\" value=\"\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Change this to a button or input when using this as a form -->\r\n");
      out.write("                            <a href=\"javascript:void(0)\" class=\"btn btn-lg btn-success btn-block\" id='login_btn'>登录</a>\r\n");
      out.write("                        </fieldset>\r\n");
      out.write("                    </form>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("    $(function () {\r\n");
      out.write("        $(\"#login_btn\").click(function () {\r\n");
      out.write("            $.ajax({\r\n");
      out.write("                url:\"/hrms/dologin\",\r\n");
      out.write("                type:\"POST\",\r\n");
      out.write("                data:$(\"#login_form\").serialize(),\r\n");
      out.write("                success:function (result) {\r\n");
      out.write("                    console.info(result)\r\n");
      out.write("                    if(result.code == 100){\r\n");
      out.write("                        window.location.href= \"/hrms/main\";\r\n");
      out.write("                    }else {\r\n");
      out.write("                        alert(result.extendInfo.login_error);\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("            });\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}