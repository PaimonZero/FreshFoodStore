<%-- 
    Document   : errorPage
    Created on : Sep 30, 2024, 6:21:58 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Body Customer Dashboard</title>
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="./css/bootstrap.min.css"/>
        <link rel="stylesheet" href="./css/customerCss/errorPage.css">    
    </head>
    <body>
        <%@include file = "HeaderLogin.jsp" %>
        <div style="padding-top: 50px;">
            <div class="container text-center py-5">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="error-page-content">
                            <img src="./images/Illustration.png" alt="404 Illustration" class="img-fluid mb-4">
                            <h1 class="error-code">404</h1>
                            <p class="error-message">Oops! page not found</p>
                            <p class="error-description">
                                Ut consequat ac tortor eu vehicula. Aenean accumsan purus eros. 
                                Maecenas sagittis tortor at metus mollis.
                            </p>
                            <a href="index.html" class="btn btn-success">Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="./js/bootstrap.bundle.min.js"></script>
    </body>
</html>
