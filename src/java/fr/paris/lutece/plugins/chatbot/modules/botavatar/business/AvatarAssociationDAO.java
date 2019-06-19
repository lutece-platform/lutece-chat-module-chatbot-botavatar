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

package fr.paris.lutece.plugins.chatbot.modules.botavatar.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for AvatarAssociation objects
 */
public final class AvatarAssociationDAO implements IAvatarAssociationDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_avatar_association, response, avatar_url FROM botavatar_associations WHERE id_avatar_association = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO botavatar_associations ( response, avatar_url ) VALUES ( ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM botavatar_associations WHERE id_avatar_association = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE botavatar_associations SET id_avatar_association = ?, response = ?, avatar_url = ? WHERE id_avatar_association = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_avatar_association, response, avatar_url FROM botavatar_associations";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_avatar_association FROM botavatar_associations";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( AvatarAssociation avatarAssociation, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin );
        try
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++, avatarAssociation.getResponse( ) );
            daoUtil.setString( nIndex++, avatarAssociation.getAvatarUrl( ) );

            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) )
            {
                avatarAssociation.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        finally
        {
            daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public AvatarAssociation load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );
        AvatarAssociation avatarAssociation = null;

        if ( daoUtil.next( ) )
        {
            avatarAssociation = new AvatarAssociation( );
            int nIndex = 1;

            avatarAssociation.setId( daoUtil.getInt( nIndex++ ) );
            avatarAssociation.setResponse( daoUtil.getString( nIndex++ ) );
            avatarAssociation.setAvatarUrl( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free( );
        return avatarAssociation;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( AvatarAssociation avatarAssociation, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        int nIndex = 1;

        daoUtil.setInt( nIndex++, avatarAssociation.getId( ) );
        daoUtil.setString( nIndex++, avatarAssociation.getResponse( ) );
        daoUtil.setString( nIndex++, avatarAssociation.getAvatarUrl( ) );
        daoUtil.setInt( nIndex, avatarAssociation.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<AvatarAssociation> selectAvatarAssociationsList( Plugin plugin )
    {
        List<AvatarAssociation> avatarAssociationList = new ArrayList<AvatarAssociation>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            AvatarAssociation avatarAssociation = new AvatarAssociation( );
            int nIndex = 1;

            avatarAssociation.setId( daoUtil.getInt( nIndex++ ) );
            avatarAssociation.setResponse( daoUtil.getString( nIndex++ ) );
            avatarAssociation.setAvatarUrl( daoUtil.getString( nIndex++ ) );

            avatarAssociationList.add( avatarAssociation );
        }

        daoUtil.free( );
        return avatarAssociationList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdAvatarAssociationsList( Plugin plugin )
    {
        List<Integer> avatarAssociationList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            avatarAssociationList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return avatarAssociationList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectAvatarAssociationsReferenceList( Plugin plugin )
    {
        ReferenceList avatarAssociationList = new ReferenceList( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            avatarAssociationList.addItem( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
        }

        daoUtil.free( );
        return avatarAssociationList;
    }
}
