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
 * @version CS4250 Fall 2014 (22 August 2014)
 */
public class Thing42Test {

    // private Thing42<null,null> empty;
    private Thing42<String, String>  rgbBlack;
    private Thing42<String, String>  rgbRed;
    private Thing42<String, String>  rgbRandom;
    private Thing42<Integer, String> intString1;
    private Thing42<Integer, String> intString2;
    private Thing42<Integer, String> nullIntString;

    @Before
    public void setUp() {

        // Black is the 1st level
        rgbBlack      = new Thing42<String, String> ("#000000", 1, "Black");
        rgbRed        = new Thing42<String, String> ("#FF0000", 2, "Red");
        rgbRandom     = new Thing42<String, String> ("#FF123A", 3, "Random");
        intString1    = new Thing42<Integer, String> (1, 1, "one");
        intString2    = new Thing42<Integer, String> (1, 2, "two");
        nullIntString = new Thing42<Integer, String> ();
    }

    /**
     * Test of addPeer method, of class Thing42.
     */
    @Test
    public void testAddPeer() {
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());

        // Should we be able to do this?
        rgbBlack.addPeer(rgbRandom);
        assertEquals(2, rgbBlack.getPeersAsCollection().size());
        assertEquals(true, rgbBlack.getPeersAsCollection().contains(rgbRed));
        assertEquals(true, rgbBlack.getPeersAsCollection()
                .contains(intString1));
        rgbBlack.addPeer(null);
        assertEquals(3, rgbBlack.getPeersAsCollection().size());
    }

    /**
     * Test of addPeer method, of class Thing42.
     * specified peer is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAddPeerNPExc() {
        rgbBlack.addPeer(null);
    }

    /**
     * Test of appendToPool method, of class Thing42.
     */
    @Test
    public void testAppendToPool() {
        assertEquals(0, rgbBlack.getPoolAsList().size());
        rgbBlack.appendToPool(rgbRed);
        rgbBlack.appendToPool(rgbRandom);
        assertEquals(2, rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing1 =
                (Thing42orNull) rgbBlack.getPoolAsList().get(0);

        assertEquals("Red", thing1.getData());

        Thing42orNull<String, String> thing2 =
                (Thing42orNull) rgbBlack.getPoolAsList().get(1);

        assertEquals("one", thing2.getData());
    }

    /**
     * Test of appendToPool method, of class Thing42.
     * specified item is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAppendToPoolNPExc() {
        rgbBlack.appendToPool(null);
    }

    /**
     * Test of getData method, of class Thing42.
     */
    @Test
    public void testGetData() {
        assertEquals("Black", rgbBlack.getData());
        assertEquals("one", intString1.getData());
        assertEquals(null, nullIntString.getData());
    }

    /**
     * Test of getKey method, of class Thing42.
     */
    @Test
    public void testGetKey() {
        assertEquals("#000000", rgbBlack.getKey());
        assertEquals(1, (long) intString1.getKey());
        assertEquals(null, nullIntString.getKey());
    }

    /**
     * Test of getLevel method, of class Thing42.
     */
    @Test
    public void testGetLevel() {
        assertEquals(1, rgbBlack.getLevel());
        assertEquals(2, rgbRed.getLevel());
        assertEquals(2, intString2.getLevel());
    }

    /**
     * Test of getOnePeer method, of class Thing42.
     */
    @Test
    public void testGetOnePeer() {
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());

        Thing42orNull<String, String> onePeer =
                rgbBlack.getOnePeer("#FF0000");

        assertEquals("Red", onePeer.getData());
    }

    /**
     * Test of getOnePeer method, of class Thing42.
     * specified Key is null so NullPointerException is thrown
     */
    @Test(expected = NullPointerException.class)
    public void testGetOnePeerNPExc() {
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());

        Thing42orNull<String, String> anotherPeer =
                rgbBlack.getOnePeer("#FF000");

        assertEquals(null, anotherPeer.getData());
    }

    /**
     * Test of getPeersAsCollection method, of class Thing42.
     */
    @Test
    public void testGetPeersAsCollection_0args() {
        rgbBlack = new Thing42<String, String>("#000000", 1, "Black");
        rgbRed   = new Thing42<String, String>("#FF0000", 2, "Red");
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());
    }

    /**
     * Test of getPeersAsCollection method, of class Thing42.
     */
    @Test
    public void testGetPeersAsCollection_GenericType() {
        assertEquals(0, rgbBlack.getPeersAsCollection("#FF0000").size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection("#FF0000").size());

        for (Thing42orNull peer : (HashSet<Thing42orNull>)
                rgbBlack.getPeersAsCollection("#FF0000")) {
            assertTrue(peer.getData().equals("Red"));
        }
    }

    /**
     * Test of getPoolAsList method, of class Thing42.
     */
    @Test
    public void testGetPoolAsList() {

        // List<Thing42orNull> poolList = rgbBlack.getPoolAsList();
        assertEquals(0, rgbBlack.getPoolAsList().size());
        rgbBlack.appendToPool(rgbRed);
        assertEquals(1, rgbBlack.getPoolAsList().size());

        // add a Thing42 with a different key and data data types
        rgbBlack.appendToPool(rgbRandom);
        assertEquals(2, rgbBlack.getPoolAsList().size());
        rgbBlack.removeFromPool(rgbRed);
        assertEquals(1, rgbBlack.getPoolAsList().size());
        assertEquals(true, rgbBlack.getPoolAsList().contains(intString1));
    }

    /**
     * Test of removeFromPool method, of class Thing42.
     */
    @Test
    public void testRemoveFromPool() {
        assertEquals(0, rgbBlack.getPoolAsList().size());
        rgbBlack.appendToPool(rgbRed);
        rgbBlack.appendToPool(rgbRandom);
        assertEquals(2, rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing1 =
                (Thing42orNull) rgbBlack.getPoolAsList().get(0);

        assertEquals("Red", thing1.getData());

        Thing42orNull<String, String> thing2 =
                (Thing42orNull) rgbBlack.getPoolAsList().get(1);

        assertEquals("one", thing2.getData());
        assertEquals(true, rgbBlack.removeFromPool(rgbRed));
        assertEquals(false, rgbBlack.removeFromPool(rgbRed));
        assertEquals(1, rgbBlack.getPoolAsList().size());

        Thing42orNull<String, String> thing3 =
                (Thing42orNull) rgbBlack.getPoolAsList().get(0);

        assertEquals("one", thing3.getData());
    }

    /**
     * Test of removeFromPool method, of class Thing42.
     * Specified parameter is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveFromPoolNPExc() {
        assertEquals(0, rgbBlack.getPoolAsList().size());
        rgbBlack.appendToPool(rgbRed);
        rgbBlack.appendToPool(rgbRandom);
        assertEquals(true, rgbBlack.removeFromPool(rgbRed));
        rgbBlack.removeFromPool(null);
    }

    /**
     * Test of removePeer method, of class Thing42.
     */
    @Test
    public void testRemovePeer() {
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());

        // Should we be able to do this?
        rgbBlack.addPeer(rgbRandom);
        assertEquals(2, rgbBlack.getPeersAsCollection().size());
        assertEquals(true, rgbBlack.getPeersAsCollection().contains(rgbRed));
        assertEquals(true, rgbBlack.getPeersAsCollection()
                .contains(intString1));
        assertEquals(true, rgbBlack.removePeer(rgbRed));
        assertEquals(false, rgbBlack.removePeer(rgbRed));
        assertEquals(false, rgbBlack.getPeersAsCollection().contains(rgbRed));
        assertEquals(1, rgbBlack.getPeersAsCollection().size());
    }

    /**
     * Test of removePeer method, of class Thing42.
     * Specified parameter is null so expected=NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemovePeerNPExc() {
        assertEquals(0, rgbBlack.getPeersAsCollection().size());
        rgbBlack.addPeer(rgbRed);
        assertEquals(1, rgbBlack.getPeersAsCollection().size());

        // Should we be able to do this?
        rgbBlack.addPeer(rgbRandom);
        assertEquals(2, rgbBlack.getPeersAsCollection().size());
        assertEquals(true, rgbBlack.removePeer(rgbRed));
        assertEquals(false, rgbBlack.removePeer(null));
    }

    /**
     * Test of setData method, of class Thing42.
     */
    @Test
    public void testSetData() {
        assertEquals("Black", rgbBlack.getData());
        rgbBlack.setData("Purple");
        assertEquals("Purple", rgbBlack.getData());
        assertEquals("one", intString1.getData());
        intString1.setData("three");
        assertEquals("three", intString1.getData());
        assertEquals(null, nullIntString.getData());
        nullIntString.setData("new");
        assertEquals("new", nullIntString.getData());
    }
}
