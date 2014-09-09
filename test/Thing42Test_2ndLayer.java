import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.List;

/**
 * Provides a 2nd layer of tests for the Thing42 class.
 *
 * @author Josh Gillham
 * @version 8-23-2014
 */
public class Thing42Test_2ndLayer {
    
    /**
     * Constructs the class (unused).
     */
    public Thing42Test_2ndLayer() {
    }
    
    /**
     * Sets up the class (unused).
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     * Tears down the class (unused).
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Sets up the test case (unused).
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Tears down the test case (unused).
     */
    @After
    public void tearDown() {
    }
    
    /**
     * Tests adding one peer.
     */
    @Test
    public void testAddPeer()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer = new Thing42( "key1", 1, "Data2" );
        instance.addPeer( peer );
    }

    /**
     * Tests adding a null peer and expects an exception.
     */
    @Test( expected = NullPointerException.class )
    public void testAddPeer_onNull()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.addPeer( null );
    }

    /**
     * Tests appending a member to the pool.
     */
    @Test
    public void testAppendToPool()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 member = new Thing42( "key1", 1, "Data2" );
        instance.appendToPool( member );
    }

    /**
     * Tests appending a null to the pool and expects an exception.
     */
    @Test( expected = NullPointerException.class )
    public void testAppendToPool_onNull()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.appendToPool( null );
    }

    /**
     * Tests accessing the data.
     */
    @Test
    public void testGetData()
    {
        String data = "Data";
        Thing42 instance = new Thing42( "key1", 1, data );
        assertEquals( instance.getData(), data );
    }

    /**
     * Tests accessing the level.
     */
    @Test
    public void testGetLevel()
    {
        String data = "Data";
        long level = 23213;
        Thing42 instance = new Thing42( "key1", level, data );
        assertEquals( instance.getLevel(), level );
    }

    /**
     * Tests accessing the peer with the specified key.
     */
    @Test
    public void testGetOnePeer()
    {
        String key = "key2";
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer = new Thing42( key, 1, "Data2" );
        instance.addPeer( peer );
        assertEquals( instance.getOnePeer( key ), peer );
    }

    /**
     * Tests accessing an Object which is not a peer.
     */
    @Test
    public void testGetOnePeer_onNotFound()
    {
        String key = "key2";
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer = new Thing42( key, 1, "Data2" );
        Thing42 peer2 = new Thing42( key + 5, 1, "Data2" );
        instance.addPeer( peer );
        assertNull( instance.getOnePeer( key + 5 ) );
    }

    /**
     * Tests accessing all peers.
     */
    @Test
    public void testGetPeersAsCollection()
    {
        String key = "key";
        Thing42 instance = new Thing42( key, 0, 25 );
        final int MAX = 10;
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 peer = new Thing42( key + i, i + 1, i * 50 );
            instance.addPeer( peer );
        }
        Collection peers = instance.getPeersAsCollection();
        assertEquals( peers.size(), MAX );
    }

    /**
     * Tests accessing peers when there are no peers.
     */
    @Test
    public void testGetPeersAsCollection_onNoPeers()
    {
        String key = "key";
        Thing42 instance = new Thing42( key, 0, 25 );
        Collection peers = instance.getPeersAsCollection();
        assertEquals( peers.size(), 0 );
    }

    /**
     * Tests accessing all peers that match a specified key.
     */
    @Test
    public void testGetPeersAsCollection_Key()
    {
        String key = "key";
        String subsetKey = "ID123";
        Thing42 instance = new Thing42( key, 0, 25 );
        final int MAX = 10;
        final int SUBSET_MAX = 30;
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 peer = new Thing42( key + i, i + 1, i * 50 );
            instance.addPeer( peer );
        }
        for ( int i = 0; i < SUBSET_MAX; ++i )
        {
            Thing42 peer = new Thing42( subsetKey, i + 1, i * 50 );
            instance.addPeer( peer );
        }
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 peer = new Thing42( key + i, i + 1, i * 50 );
            instance.addPeer( peer );
        }
        Collection peers = instance.getPeersAsCollection( subsetKey );
        assertEquals( peers.size(), SUBSET_MAX );
    }

    /**
     * Tests accessing all peers that match a specified key
     *  when there are no matches.
     */
    @Test
    public void testGetPeersAsCollection_Key_onNoMatch()
    {
        String key = "key";
        String subsetKey = "ID123";
        Thing42 instance = new Thing42( key, 0, 25 );
        final int MAX = 10;
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 peer = new Thing42( key + i, i + 1, i * 50 );
            instance.addPeer( peer );
        }
        Collection peers = instance.getPeersAsCollection( subsetKey );
        assertEquals( peers.size(), 0 );
    }

    /**
     * Tests accessing all pool members.
     */
    @Test
    public void testGetPoolAsList()
    {
        String key = "key";
        Thing42 instance = new Thing42( key, 0, 25 );
        final int MAX = 10;
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 member = new Thing42( key + i, i + 1, i * 50 );
            instance.appendToPool( member );
        }
        List pool = instance.getPoolAsList( );
        assertEquals( pool.size(), MAX );
    }
    /**
     * Tests accessing all pool members when there are no members.
     */
    @Test
    public void testGetPoolAsList_onNoMembers()
    {
        String key = "key";
        Thing42 instance = new Thing42( key, 0, 25 );
        List pool = instance.getPoolAsList( );
        assertEquals( pool.size(), 0 );
    }
    /**
     * Tests to ensure that the container of pool members is orderly.
     */
    @Test
    public void testGetPoolAsList_isOrderly()
    {
        String key = "key";
        Thing42 instance = new Thing42( key, 0, 25 );
        final int MAX = 10;
        final int SUBSET_MAX = 30;
        for ( int i = 0; i < MAX; ++i )
        {
            Thing42 member = new Thing42( key + i, i + 1, i * 50 );
            instance.appendToPool( member );
        }
        List<Thing42> pool = instance.getPoolAsList( );
        int i = 0;
        for ( Thing42 item : pool )
        {
            assertEquals( item.getKey(), key + i );
            assertEquals( item.getLevel(), i + 1 );
            assertEquals( item.getData(), i * 50 );
            ++i;
        }
    }
    /**
     * Tests removing a member from the pool.
     */
    @Test
    public void testRemoveFromPool()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 member= new Thing42( "key1", 1, "Data2" );
        instance.appendToPool( member );
        assertTrue( instance.removeFromPool( member ) );
    }
    /**
     * Tests removing a member which does not exist in the pool.
     */
    @Test
    public void testRemoveFromPool_notFound()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 member= new Thing42( "key1", 1, "Data2" );
        assertFalse( instance.removeFromPool( member ) );
    }
    /**
     * Tests removing a null and expects an exception.
     */
    @Test( expected = NullPointerException.class )
    public void testRemoveFromPool_onNull()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.removeFromPool( null );
    }
    /**
     * Tests removing a peer.
     */
    @Test
    public void testRemovePeer()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer = new Thing42( "key1", 1, "Data2" );
        instance.addPeer( peer );
        assertTrue( instance.removePeer( peer ) );
    }
    /**
     * Tests removing an object which is not a peer.
     */
    @Test
    public void testRemovePeer_notFound()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer = new Thing42( "key1", 1, "Data2" );
        assertFalse( instance.removePeer( peer ) );
    }
    /**
     * Tests removing a null and expects an exception.
     */
    @Test( expected = NullPointerException.class )
    public void testRemovePeer_onNull()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.removePeer( null );
    }
    /**
     * Tests setting the data and ensures the data was changed.
     */
    @Test
    public void testSetData()
    {
        String newData = "123";
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.setData( newData );
        assertEquals( instance.getData(), newData );
    }
    /**
     * Tests setting the data to a null.
     */
    @Test
    public void testSetData_usingNull()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        instance.setData( null );
        assertNull( instance.getData() );
    }
    
    /**
     * Tests adding one peer.
     */
    @Test
    public void testCircularDependency()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer1 = new Thing42( "key2", 2, "Data3" );
        Thing42 peer2 = new Thing42( "key3", 3, "Data4" );
        instance.addPeer( peer1 );
        peer1.addPeer( peer2 );
        peer2.addPeer( instance );
        assertNotEquals( peer1, peer2 );
    }
}
