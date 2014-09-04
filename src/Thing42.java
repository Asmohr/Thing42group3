//~--- JDK imports ------------------------------------------------------------
import java.util.Collection;
import java.util.List;

/**
 * Master Thing42 API that duplicates Jody's online. Will add the remaining
 * comments before final review.
 *
 * @author Group 3 - MW - From Jody Paul's API
 * @version CS4250 Fall 2014 - API - (3 September 2014)
 * @param <K> the type of key
 * @param <D> the type of data
 */
public class Thing42<K, D> implements Thing42orNull<K, D> {

    /**
     * Constructor for objects of class Thing42.
     *
     * @param key the key of this object
     * @param level the level of this object
     * @param data the data of this object
     */
    public Thing42(final K key, final long level, final D data) {
    }

    /**
     * Add a peer to this object.
     *
     * @param newPeer the peer to be added
     * @throws NullPointerException if the specified peer is null
     */
    public void addPeer(Thing42orNull<K, D> newPeer) throws NullPointerException {
    }

    /**
     * Append a member to the pool of this object.
     *
     * @param newMember the object to be appended to the pool
     * @throws NullPointerException if the specified item is null
     */
    public void appendToPool(Thing42orNull<K, D> newMember)
            throws NullPointerException {
    }

    /**
     * <p>
     * Determines whether or not the specified Object is equal to this Thing42.
     * The specified Object is equal to this Thing42 if it is an instance of
     * Thing42; if its level is the same as this Thing42; and if its key, data,
     * peers, and pool are the same as this Thing42 via the equals predicate.
     *
     * @param obj an Object to be compared with this Thing42orNull.
     * @return true if obj is an instance of Thing42 and has the same values;
     * false otherwise.
     * @see hashCode()
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     * Access the data of this Thing42.
     *
     * @return the data of this object
     */
    public D getData() {
        return null;
    }

    /**
     * Access the key of this Thing42.
     *
     * @return the key of this object
     */
    public K getKey() {
        return null;
    }

    /**
     * Access the level of this Thing42.
     *
     * @return the level of this object
     */
    public long getLevel() {
        return 0;
    }

    /**
     * Access a peer matching the specified key.
     *
     * @param key the search key
     * @return any peer known by this object that matches the given key; null if
     * no match
     */
    public Thing42orNull<K, D> getOnePeer(K key) {
        return null;
    }

    /**
     * Access all peers.
     *
     * @return all peers known by this object; if no peers then returns a
     * collection with size() == 0.
     */
    public Collection<Thing42orNull<K, D>> getPeersAsCollection() {
        return null;
    }

    /**
     * Access all peers matching the specified key.
     *
     * @param key the search key
     * @return all peers known by this object that match the given key; if no
     * peer matches then returns a collection with size() == 0.
     */
    public Collection<Thing42orNull<K, D>> getPeersAsCollection(K key) {
        return null;
    }

    /**
     * Access all members of the pool.
     *
     * @return all members of the pool known by this object; if no members then
     * returns a list with size() == 0.
     */
    public List<Thing42orNull<K, D>> getPoolAsList() {
        return null;
    }

    /**
     * Returns the hashcode for this Thing42.
     *
     * @return the hashcode for this Thing42
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Remove a single instance of the specified object from this object's pool.
     *
     * @param member the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException if the specified parameter is null
     */
    public boolean removeFromPool(Thing42orNull<K, D> member)
            throws NullPointerException {
        return false;
    }

    /**
     * Remove a single instance of the specified peer from this object.
     *
     * @param peer the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException if the specified peer is null
     */
    public boolean removePeer(Thing42orNull<K, D> peer)
            throws NullPointerException {
        return false;
    }

    /**
     * Modify the data of this Thing42.
     *
     * @param newData the updated data for this object
     */
    public void setData(D newData) {
    }
}
