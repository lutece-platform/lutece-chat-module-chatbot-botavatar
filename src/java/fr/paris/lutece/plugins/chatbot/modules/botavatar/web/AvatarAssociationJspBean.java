/*
 * Copyright (c) 2002-2019, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.chatbot.modules.botavatar.web;

import fr.paris.lutece.plugins.chatbot.modules.botavatar.business.AvatarAssociation;
import fr.paris.lutece.plugins.chatbot.modules.botavatar.business.AvatarAssociationHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * This class provides the user interface to manage AvatarAssociation features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageAvatarAssociations.jsp", controllerPath = "jsp/admin/plugins/botavatar/", right = "BOTAVATAR_MANAGEMENT" )
public class AvatarAssociationJspBean extends AbstractManageChatbotAvatarsJspBean
{
    // Templates
    private static final String TEMPLATE_MANAGE_AVATARASSOCIATIONS = "/admin/plugins/chatbot/modules/botavatar/manage_avatarassociations.html";
    private static final String TEMPLATE_CREATE_AVATARASSOCIATION = "/admin/plugins/chatbot/modules/botavatar/create_avatarassociation.html";
    private static final String TEMPLATE_MODIFY_AVATARASSOCIATION = "/admin/plugins/chatbot/modules/botavatar/modify_avatarassociation.html";

    // Parameters
    private static final String PARAMETER_ID_AVATARASSOCIATION = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_AVATARASSOCIATIONS = "module.chatbot.botavatar.manage_avatarassociations.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_AVATARASSOCIATION = "module.chatbot.botavatar.modify_avatarassociation.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_AVATARASSOCIATION = "module.chatbot.botavatar.create_avatarassociation.pageTitle";

    // Markers
    private static final String MARK_AVATARASSOCIATION_LIST = "avatarassociation_list";
    private static final String MARK_AVATARASSOCIATION = "avatarassociation";

    private static final String JSP_MANAGE_AVATARASSOCIATIONS = "jsp/admin/plugins/chatbot/modules/botavatar/ManageAvatarAssociations.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_AVATARASSOCIATION = "module.chatbot.botavatar.message.confirmRemoveAvatarAssociation";

    // Validations
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "module.chatbot.botavatar.model.entity.avatarassociation.attribute.";

    // Views
    private static final String VIEW_MANAGE_AVATARASSOCIATIONS = "manageAvatarAssociations";
    private static final String VIEW_CREATE_AVATARASSOCIATION = "createAvatarAssociation";
    private static final String VIEW_MODIFY_AVATARASSOCIATION = "modifyAvatarAssociation";

    // Actions
    private static final String ACTION_CREATE_AVATARASSOCIATION = "createAvatarAssociation";
    private static final String ACTION_MODIFY_AVATARASSOCIATION = "modifyAvatarAssociation";
    private static final String ACTION_REMOVE_AVATARASSOCIATION = "removeAvatarAssociation";
    private static final String ACTION_CONFIRM_REMOVE_AVATARASSOCIATION = "confirmRemoveAvatarAssociation";

    // Infos
    private static final String INFO_AVATARASSOCIATION_CREATED = "module.chatbot.botavatar.info.avatarassociation.created";
    private static final String INFO_AVATARASSOCIATION_UPDATED = "module.chatbot.botavatar.info.avatarassociation.updated";
    private static final String INFO_AVATARASSOCIATION_REMOVED = "module.chatbot.botavatar.info.avatarassociation.removed";

    // Session variable to store working values
    private AvatarAssociation _avatarassociation;

    /**
     * Build the Manage View
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_MANAGE_AVATARASSOCIATIONS, defaultView = true )
    public String getManageAvatarAssociations( HttpServletRequest request )
    {
        _avatarassociation = null;
        List<AvatarAssociation> listAvatarAssociations = AvatarAssociationHome.getAvatarAssociationsList( );
        Map<String, Object> model = getPaginatedListModel( request, MARK_AVATARASSOCIATION_LIST, listAvatarAssociations, JSP_MANAGE_AVATARASSOCIATIONS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_AVATARASSOCIATIONS, TEMPLATE_MANAGE_AVATARASSOCIATIONS, model );
    }

    /**
     * Returns the form to create a avatarassociation
     *
     * @param request
     *            The Http request
     * @return the html code of the avatarassociation form
     */
    @View( VIEW_CREATE_AVATARASSOCIATION )
    public String getCreateAvatarAssociation( HttpServletRequest request )
    {
        _avatarassociation = ( _avatarassociation != null ) ? _avatarassociation : new AvatarAssociation( );

        Map<String, Object> model = getModel( );
        model.put( MARK_AVATARASSOCIATION, _avatarassociation );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_AVATARASSOCIATION, TEMPLATE_CREATE_AVATARASSOCIATION, model );
    }

    /**
     * Process the data capture form of a new avatarassociation
     *
     * @param request
     *            The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_AVATARASSOCIATION )
    public String doCreateAvatarAssociation( HttpServletRequest request )
    {
        populate( _avatarassociation, request, request.getLocale( ) );

        // Check constraints
        if ( !validateBean( _avatarassociation, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_AVATARASSOCIATION );
        }

        AvatarAssociationHome.create( _avatarassociation );
        addInfo( INFO_AVATARASSOCIATION_CREATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_AVATARASSOCIATIONS );
    }

    /**
     * Manages the removal form of a avatarassociation whose identifier is in the http request
     *
     * @param request
     *            The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_AVATARASSOCIATION )
    public String getConfirmRemoveAvatarAssociation( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_AVATARASSOCIATION ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_AVATARASSOCIATION ) );
        url.addParameter( PARAMETER_ID_AVATARASSOCIATION, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_AVATARASSOCIATION, url.getUrl( ),
                AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a avatarassociation
     *
     * @param request
     *            The Http request
     * @return the jsp URL to display the form to manage avatarassociations
     */
    @Action( ACTION_REMOVE_AVATARASSOCIATION )
    public String doRemoveAvatarAssociation( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_AVATARASSOCIATION ) );
        AvatarAssociationHome.remove( nId );
        addInfo( INFO_AVATARASSOCIATION_REMOVED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_AVATARASSOCIATIONS );
    }

    /**
     * Returns the form to update info about a avatarassociation
     *
     * @param request
     *            The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_AVATARASSOCIATION )
    public String getModifyAvatarAssociation( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_AVATARASSOCIATION ) );

        if ( _avatarassociation == null || ( _avatarassociation.getId( ) != nId ) )
        {
            _avatarassociation = AvatarAssociationHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel( );
        model.put( MARK_AVATARASSOCIATION, _avatarassociation );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_AVATARASSOCIATION, TEMPLATE_MODIFY_AVATARASSOCIATION, model );
    }

    /**
     * Process the change form of a avatarassociation
     *
     * @param request
     *            The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_AVATARASSOCIATION )
    public String doModifyAvatarAssociation( HttpServletRequest request )
    {
        populate( _avatarassociation, request, request.getLocale( ) );

        // Check constraints
        if ( !validateBean( _avatarassociation, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_AVATARASSOCIATION, PARAMETER_ID_AVATARASSOCIATION, _avatarassociation.getId( ) );
        }

        AvatarAssociationHome.update( _avatarassociation );
        addInfo( INFO_AVATARASSOCIATION_UPDATED, getLocale( ) );

        return redirectView( request, VIEW_MANAGE_AVATARASSOCIATIONS );
    }
}
