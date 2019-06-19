<jsp:useBean id="managechatbotavatarsAvatarAssociation" scope="session" class="fr.paris.lutece.plugins.botavatar.web.AvatarAssociationJspBean" />
<% String strContent = managechatbotavatarsAvatarAssociation.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
