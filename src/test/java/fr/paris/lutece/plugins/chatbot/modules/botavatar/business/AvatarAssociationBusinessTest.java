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

import fr.paris.lutece.plugins.chatbot.modules.botavatar.business.AvatarAssociationHome;
import fr.paris.lutece.plugins.chatbot.modules.botavatar.business.AvatarAssociation;
import fr.paris.lutece.test.LuteceTestCase;

/**
 * This is the business class test for the object AvatarAssociation
 */
public class AvatarAssociationBusinessTest extends LuteceTestCase
{
    private static final String RESPONSE1 = "Response1";
    private static final String RESPONSE2 = "Response2";
    private static final String AVATARURL1 = "AvatarUrl1";
    private static final String AVATARURL2 = "AvatarUrl2";

    /**
     * test AvatarAssociation
     */
    public void testBusiness( )
    {
        // Initialize an object
        AvatarAssociation avatarAssociation = new AvatarAssociation( );
        avatarAssociation.setResponse( RESPONSE1 );
        avatarAssociation.setAvatarUrl( AVATARURL1 );

        // Create test
        AvatarAssociationHome.create( avatarAssociation );
        AvatarAssociation avatarAssociationStored = AvatarAssociationHome.findByPrimaryKey( avatarAssociation.getId( ) );
        assertEquals( avatarAssociationStored.getResponse( ), avatarAssociation.getResponse( ) );
        assertEquals( avatarAssociationStored.getAvatarUrl( ), avatarAssociation.getAvatarUrl( ) );

        // Update test
        avatarAssociation.setResponse( RESPONSE2 );
        avatarAssociation.setAvatarUrl( AVATARURL2 );
        AvatarAssociationHome.update( avatarAssociation );
        avatarAssociationStored = AvatarAssociationHome.findByPrimaryKey( avatarAssociation.getId( ) );
        assertEquals( avatarAssociationStored.getResponse( ), avatarAssociation.getResponse( ) );
        assertEquals( avatarAssociationStored.getAvatarUrl( ), avatarAssociation.getAvatarUrl( ) );

        // List test
        AvatarAssociationHome.getAvatarAssociationsList( );

        // Delete test
        AvatarAssociationHome.remove( avatarAssociation.getId( ) );
        avatarAssociationStored = AvatarAssociationHome.findByPrimaryKey( avatarAssociation.getId( ) );
        assertNull( avatarAssociationStored );

    }

}
