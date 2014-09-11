//~--- non-JDK imports --------------------------------------------------------
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------
import java.util.HashSet;

/**
 * Preliminary Programming Project
 * Test Class for Thing42 class
 *
 * @author Alex Miscall - From Jody Paul's API
 * @version CS4250 Fall 2014 (10 September 2014)
 */
public class Thing42Test {

    // private Thing42<null,null> empty;
    private Thing42<String, String>  rgbBlack;
    private Thing42<String, String>  rgbRed;
    private Thing42<String, String>  rgbRandom;
    private Thing42<Integer, String> intString1;
    private Thing42<Integer, String> intString2;

    @Before
    public void setUp() {

        // Black is the 1st level
        this.rgbBlack      = new Thing42<String, String> ("#000000", 1, "Black");
        this.rgbRed        = new Thing42<String, String> ("#FF0000", 2, "Red");
        this.rgbRandom     = new Thing42<String, String> ("#FF123A", 3, "Random");
        this.intString1    = new Thing42<Integer, String> (1, 1, "one");
        this.intString2    = new Thing42<Integer, String> (1, 2, "two");
    }

    /**
     * Test of addPeer method, of class Thing42.
     */
    @Test
    public void testAddPeer() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPeersAsCollection().size());
        assertEquals(true, this.rgbBlack.getPeersAsCollection().contains(this.rgbRed));
    }

    /**
     * Test of addPeer method, of class Thing42.
     * specified peer is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAddPeerNPExc() {
        this.rgbBlack.addPeer(null);
    }

    /**
     * Test of appendToPool method, of class Thing42.
     */
    @Test
    public void testAppendToPool() {
        assertEquals(0, this.rgbBlack.getPoolAsList().size());
        this.rgbBlack.appendToPool(this.rgbRed);
        this.rgbBlack.appendToPool(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing1 =
                (Thing42orNull) this.rgbBlack.getPoolAsList().get(0);

        assertEquals("Red", thing1.getData());

        Thing42orNull<String, String> thing2 =
                (Thing42orNull) this.rgbBlack.getPoolAsList().get(1);

        assertEquals("Random", thing2.getData());
    }

    /**
     * Test of appendToPool method, of class Thing42.
     * specified item is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAppendToPoolNPExc() {
        this.rgbBlack.appendToPool(null);
    }

    /**
     * Test of getData method, of class Thing42.
     */
    @Test
    public void testGetData() {
        assertEquals("Black", this.rgbBlack.getData());
        assertEquals("one", this.intString1.getData());
        this.rgbBlack.setData(null);
        assertNull( this.rgbBlack.getData());
    }
    
    /**
    * Test to ensure duplicates can be added to peers and the pool.
    */
    @Test
    public void duplicateTest(){
       Thing42<String, String> rgbBlack2 = new Thing42<String, String> ("#000000", 1, "Black");
       this.rgbRed.addPeer(this.rgbBlack);
       this.rgbRed.addPeer(rgbBlack2);
       assertTrue(this.rgbRed.getPeersAsCollection().size() == 2);
       this.rgbRed.appendToPool(this.rgbBlack);
       this.rgbRed.appendToPool(this.rgbBlack);
       assertTrue(this.rgbRed.getPoolAsList().size() == 2);
    }

    /**
     * Test of getKey method, of class Thing42.
     */
    @Test
    public void testGetKey() {
        assertEquals("#000000", this.rgbBlack.getKey());
        assertEquals(1, (long) this.intString1.getKey());
    }

    /**
     * Test of getLevel method, of class Thing42.
     */
    @Test
    public void testGetLevel() {
        assertEquals(1, this.rgbBlack.getLevel());
        assertEquals(2, this.rgbRed.getLevel());
        assertEquals(2, this.intString2.getLevel());
    }

    /**
     * Test of getOnePeer method, of class Thing42.
     */
    @Test
    public void testGetOnePeer() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());

        Thing42orNull<String, String> onePeer =
                this.rgbBlack.getOnePeer("#FF0000");

        assertEquals("Red", onePeer.getData());
    }

    /**
     * Test of getOnePeer method, of class Thing42.
     * specified Key is null so NullPointerException is thrown
     */
    @Test(expected = NullPointerException.class)
    public void testGetOnePeerNPExc() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());

        Thing42orNull<String, String> anotherPeer =
                this.rgbBlack.getOnePeer("#FF000");

        assertEquals(null, anotherPeer.getData());
    }

    /**
     * Test of getPeersAsCollection method, of class Thing42.
     */
    @Test
    public void testGetPeersAsCollection_0args() {
        this.rgbBlack = new Thing42<String, String>("#000000", 1, "Black");
        this.rgbRed   = new Thing42<String, String>("#FF0000", 2, "Red");
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());
    }

    /**
     * Test of getPeersAsCollection method, of class Thing42.
     */
    @Test
    public void testGetPeersAsCollection_GenericType() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection("#FF0000").size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection("#FF0000").size());

        for (Thing42orNull peer :
                this.rgbBlack.getPeersAsCollection("#FF0000")) {
            assertTrue(peer.getData().equals("Red"));
        }
    }

    /**
     * Test of getPoolAsList method, of class Thing42.
     */
    @Test
    public void testGetPoolAsList() {

        assertEquals(0, this.rgbBlack.getPoolAsList().size());
        this.rgbBlack.appendToPool(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPoolAsList().size());

        // add a Thing42 with a different key and data data types
        this.rgbBlack.appendToPool(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPoolAsList().size());
        this.rgbBlack.removeFromPool(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPoolAsList().size());
        assertFalse(this.rgbBlack.getPoolAsList().contains(this.rgbRed));
        assertEquals(true, this.rgbBlack.getPoolAsList().contains(this.rgbRandom));
    }

    /**
     * Test of removeFromPool method, of class Thing42.
     */
    @Test
    public void testRemoveFromPool() {
        assertEquals(0, this.rgbBlack.getPoolAsList().size());
        this.rgbBlack.appendToPool(this.rgbRed);
        this.rgbBlack.appendToPool(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing1 = //this.rgbRed
                (Thing42orNull) this.rgbBlack.getPoolAsList().get(0);

        assertEquals("Red", thing1.getData());

        Thing42orNull<String, String> thing2 = //this.rgbRandom
                (Thing42orNull) this.rgbBlack.getPoolAsList().get(1);

        assertEquals("Random", thing2.getData());
        assertEquals(true, this.rgbBlack.removeFromPool(this.rgbRed));
        assertEquals(false, this.rgbBlack.removeFromPool(this.rgbRed));
        assertEquals(1, this.rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing3 =
                (Thing42orNull) this.rgbBlack.getPoolAsList().get(0);

        assertEquals("Random", thing3.getData());
    }

    /**
     * Test of removeFromPool method, of class Thing42.
     * Specified parameter is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveFromPoolNPExc() {
        assertEquals(0, this.rgbBlack.getPoolAsList().size());
        this.rgbBlack.appendToPool(this.rgbRed);
        this.rgbBlack.appendToPool(this.rgbRandom);
        assertEquals(true, this.rgbBlack.removeFromPool(this.rgbRed));
        this.rgbBlack.removeFromPool(null);
    }

    /**
     * Test of removePeer method, of class Thing42.
     */
    @Test
    public void testRemovePeer() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());

        // Should we be able to do this?
        this.rgbBlack.addPeer(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPeersAsCollection().size());
        assertEquals(true, this.rgbBlack.getPeersAsCollection().contains(this.rgbRed));
        assertEquals(false, this.rgbBlack.getPeersAsCollection()
                .contains(this.intString1));
        assertEquals(true, this.rgbBlack.removePeer(this.rgbRed));
        assertEquals(false, this.rgbBlack.removePeer(this.rgbRed));
        assertEquals(false, this.rgbBlack.getPeersAsCollection().contains(this.rgbRed));
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());
    }

    /**
     * Test of removePeer method, of class Thing42.
     * Specified parameter is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemovePeerNPExc() {
        assertEquals(0, this.rgbBlack.getPeersAsCollection().size());
        this.rgbBlack.addPeer(this.rgbRed);
        assertEquals(1, this.rgbBlack.getPeersAsCollection().size());

        // Should we be able to do this?
        this.rgbBlack.addPeer(this.rgbRandom);
        assertEquals(2, this.rgbBlack.getPeersAsCollection().size());
        assertEquals(true, this.rgbBlack.removePeer(this.rgbRed));
        assertEquals(false, this.rgbBlack.removePeer(null));
    }

    /**
     * Test of setData method, of class Thing42.
     */
    @Test
    public void testSetData() {
        assertEquals("Black", this.rgbBlack.getData());
        this.rgbBlack.setData("Purple");
        assertEquals("Purple", this.rgbBlack.getData());
        assertEquals("one", this.intString1.getData());
        this.intString1.setData("three");
        assertEquals("three", this.intString1.getData());
        //assertNull(nullIntString.getData());
    
    }
    
    /**
     * Test to enure correct results from overwritten equal method.
     */
    @Test
    public void equalsTest(){
        Thing42<String, String> rgbBlack2 = new Thing42<String, String> ("#000000", 1, "Black");
        assertTrue(this.rgbBlack.equals(rgbBlack2));
        Thing42<String, String> rgbGreen = new Thing42<String, String> ("#00FF00", 1, "Green");
        rgbBlack2.addPeer(rgbGreen);
        this.rgbBlack.addPeer(rgbGreen);
        assertTrue(this.rgbBlack.equals(rgbBlack2));
    }
    
    /**
     * Test to ensure a null pointer is thrown when comparing thing42 to null.
     */
    @Test
    public void equalsTestNull(){
        assertFalse(this.rgbBlack.equals(null));
    }
    /**
     * Test to ensure Thing42 of different Data Types don't equal.
     */
    @Test
    public void equalsTestDifferentKeysData(){
        Thing42<Integer, Integer> Inte = new Thing42<Integer, Integer> (1, 1, 1);
        Thing42<Long, Long> Longe = new Thing42<Long, Long> (1L, 1, 1L);
        assertFalse(Inte.equals(Longe));
    }
    
    /**
     * Create a Thing42 with null data and key.
     */
    @Test
    public void keyAndDataNull(){
        Thing42<Integer,Integer> nullThing = new Thing42<Integer,Integer>(null, 1, null);
        assertNull(nullThing.getData());
    }
    
    /**
     * Compare the hash to a null.
     */
    @Test
    public void hashTestNullCheck(){    
        Integer hash = this.rgbBlack.hashCode();
        assertFalse(hash.equals(null));
    }
    
    /**
     * Make sure the has equals something with the same data, level, and key.
     * Also, make sure that it is not equal to an object with a different
     * data, level, and key.
     */
    @Test
    public void hashTestEqualThings(){
        Thing42<String, String> rgbBlack2 = new Thing42<String, String>("#000000", 1, "Black");
        assertTrue(this.rgbBlack.equals(rgbBlack2));
        System.out.println(this.rgbBlack.hashCode() + " " + this.rgbRandom.hashCode() + " " + this.rgbRed.hashCode());
        assertTrue(this.rgbBlack.hashCode() == rgbBlack2.hashCode());
        assertTrue(this.rgbBlack.hashCode() != this.rgbRandom.hashCode());
        assertTrue(this.rgbBlack.hashCode() != this.rgbRed.hashCode());
    }
    
    /**
     * Tests adding one peer.
     */
    @Test
    public void testCircularDependency()
    {
        Thing42 instance = new Thing42( "key1", 1, "Data2" );
        Thing42 peer1 = new Thing42( "key1", 1, "Data2" );
        Thing42 peer2 = new Thing42( "key1", 1, "Data2" );
        instance.addPeer( peer1 );
        peer1.addPeer( peer2 );
        peer2.addPeer( instance );
        assertNotEquals( peer1, peer2 );
    }
    /**
     * Tests adding one peer.
     */
    @Test
    public void testCircularDependency2()
    {
        Thing42 peer1 = new Thing42( "key1", 1, "Data2" );
        Thing42 peer2 = new Thing42( "key1", 1, "Data2" );
        peer2.addPeer( peer1 );
        peer1.addPeer( peer2 );
        assertNotEquals( peer1, peer2 );
    }
}
