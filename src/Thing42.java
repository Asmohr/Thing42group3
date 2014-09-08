//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

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

    /** Starting prime for calculate hashcode. */
    private static final int FIRSTPRIME = 5;
    /** prime number for calculating hashcode. */
    private static final int PRIMENUM = 89;
    /** Holds the key value of this object.*/
    private final K key;
    /** Holds the level value of this object.*/
    private final long level;
    /** Holds the data value of this object.*/
    private D data;
    /** Holds the peers of this object.*/
    private Collection<Thing42orNull> peers;
    /** Holds the pool of this object.*/
    private Collection<Thing42orNull> pool;

    /**
     * Constructor for objects of class Thing42.
     *
     * @param nkey the key of this object
     * @param nlevel the level of this object
     * @param ndata the data of this object
     */
    public Thing42(final K nkey, final long nlevel, final D ndata) {
        this.key = nkey;
        this.level = nlevel;
        this.data = ndata;
        this.peers = new ArrayList<Thing42orNull>();
        this.pool = new ArrayList<Thing42orNull>();
    }

    /**
     * Add a peer to this object.
     *
     * @param newPeer the peer to be added
     * @throws NullPointerException if the specified peer is null
     */
    @Override
    public void addPeer(final Thing42orNull<K, D> newPeer)
        throws NullPointerException {
        if (newPeer == null) {
            throw new NullPointerException();
        }
        this.peers.add(newPeer);
    }

    /**
     * Append a member to the pool of this object.
     *
     * @param newMember the object to be appended to the pool
     * @throws NullPointerException if the specified item is null
     */
    @Override
    public void appendToPool(final Thing42orNull<K, D> newMember)
        throws NullPointerException {
        if (newMember == null) {
            throw new NullPointerException();
        }
        this.pool.add(newMember);
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
    public boolean equals(final Object obj) {
        /* Strings used for comparing class names of the Key and Data */
        final String thisKeyClass;
        final String objKeyClass;
        final String thisDataClass;
        final String objDataClass;
        /* Booleans used for storing the boolean when comparing the classes */
        final boolean equalKClasses;
        final boolean equalDClasses;
        /* Array lists used for comparing the two objects */
        final List<Thing42orNull<K, D>> thisPeers;
        final List<Thing42orNull<K, D>> objPeers;
        final List<Thing42orNull<K, D>> thisPool;
        final List<Thing42orNull<K, D>> objPool;
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Thing42orNull)) {
            return false;
        }

        final Thing42<K, D> thingObj = (Thing42<K, D>) obj;
        if (this.getLevel() != thingObj.getLevel()) {
            return false;
        }
        // null check both objects Key
        if (this.getKey() != null && thingObj.getKey() != null) {
            // Compare what class the Key is for both objects
            thisKeyClass = this.getKey().getClass().toString();
            objKeyClass = thingObj.getKey().getClass().toString();
            equalKClasses = thisKeyClass.equals(objKeyClass);
            if (!equalKClasses || !this.getKey().equals(thingObj.getKey())) {
                return false;
            }
        } else if (this.getKey() != null || thingObj.getKey() != null) {
            return false;
        }

        // null check both objects data
        if (this.getData() != null && thingObj.getData() != null) {
            // Compare what class the Data is for both objects
            thisDataClass = this.getData().getClass().toString();
            objDataClass = thingObj.getData().getClass().toString();
            equalDClasses = thisDataClass.equals(objDataClass);
            if (!equalDClasses || !this.getData().equals(thingObj.getData())) {
                return false;
            }
        } else if (this.getData() != null || thingObj.getData() != null) {
            return false;
        }

        /* Compare peer list */
        thisPeers
            = (ArrayList<Thing42orNull<K, D>>) this.getPeersAsCollection();
        objPeers
            = (ArrayList<Thing42orNull<K, D>>) thingObj.getPeersAsCollection();
        if (thisPeers.size() != objPeers.size()) {
            return false;
        }
        for (int i = 0; i < this.peers.size(); ++i) {
            final int thisCount
                = Collections.frequency(thisPeers, thisPeers.get(i));
            final int objCount
                = Collections.frequency(objPeers, thisPeers.get(i));
            if (thisCount != objCount) {
                return false;
            }
        }
        /* Compare pool list */
        thisPool = (ArrayList<Thing42orNull<K, D>>) this.getPoolAsList();
        objPool = (ArrayList<Thing42orNull<K, D>>) thingObj.getPoolAsList();
        if (thisPool.size() != objPool.size()) {
            return false;
        }
        for (int i = 0; i < this.pool.size(); ++i) {
            final int thisCount
                = Collections.frequency(thisPool, thisPool.get(i));
            final int objCount
                = Collections.frequency(objPool, thisPool.get(i));
            if (thisCount != objCount) {
                return false;
            }
        }

        return true;
    }

    /**
     * Access the data of this Thing42.
     *
     * @return the data of this object
     */
    @Override
    public D getData() {
        return this.data;
    }

    /**
     * Access the key of this Thing42.
     *
     * @return the key of this object
     */
    @Override
    public K getKey() {
        return this.key;
    }

    /**
     * Access the level of this Thing42.
     *
     * @return the level of this object
     */
    @Override
    public long getLevel() {
        return this.level;
    }

    /**
     * Access a peer matching the specified key.
     *
     * @param searchKey the search key
     * @return any peer known by this object that matches the given key; null if
     * no match
     */
    @Override
    public Thing42orNull<K, D> getOnePeer(final K searchKey) {
        for (Thing42orNull thing : this.peers) {
            if (thing.getKey().equals(searchKey)) {
                return thing;
            }
        }
        return null;
    }

    /**
     * Access all peers.
     *
     * @return all peers known by this object; if no peers then returns a
     * collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection() {
        //this is a deep copy
        final Collection<Thing42orNull<K, D>> returnThings
            = new ArrayList<Thing42orNull<K, D>>();
        for (Thing42orNull thing : this.peers) {
            returnThings.add(thing);
        }
        return returnThings;
        //for a shallow copy use:
        //return peers;
    }

    /**
     * Access all peers matching the specified key.
     *
     * @param sKey the search key
     * @return all peers known by this object that match the given key; if no
     * peer matches then returns a collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection(final K sKey) {
        final Collection<Thing42orNull<K, D>> returnThings
            = new ArrayList<Thing42orNull<K, D>>();
        for (Thing42orNull thing : this.peers) {
            if (thing.getKey().equals(sKey)) {
                returnThings.add(thing);
            }
        }
        return returnThings;
    }

    /**
     * Access all members of the pool.
     *
     * @return all members of the pool known by this object; if no members then
     * returns a list with size() == 0.
     */
    @Override
    public List<Thing42orNull<K, D>> getPoolAsList() {
        //this is a deep copy
        final List<Thing42orNull<K, D>> poolList
            = new ArrayList<Thing42orNull<K, D>>();
        for (Thing42orNull thing : this.pool) {
            poolList.add(thing);
        }
        return poolList;
        //for a shallow copy use:
        //return (List<Thing42orNull>) pool;
    }

    /**
     * Returns the hashcode for this Thing42.
     *
     * @return the hashcode for this Thing42
     */
    @Override
    public int hashCode() {
        int hash = FIRSTPRIME;
        int partialHashCode;

        partialHashCode = Long.valueOf(this.getLevel()).hashCode();
        hash = PRIMENUM * hash + partialHashCode;

        if (this.getKey() != null) {
            partialHashCode = this.getKey().hashCode();
        } else {
            partialHashCode = 0;
        }
        hash = PRIMENUM * hash + partialHashCode;

        if (this.getData() != null) {
            partialHashCode = this.getData().hashCode();
        } else {
            partialHashCode = 0;
        }
        hash = PRIMENUM * hash + partialHashCode;

        return hash;
    }

    /**
     * Remove a single instance of the specified object from this object's pool.
     *
     * @param member the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException if the specified parameter is null
     */
    @Override
    public boolean removeFromPool(final Thing42orNull<K, D> member)
        throws NullPointerException {
        if (member == null) {
            throw new NullPointerException();
        }
        return this.pool.remove(member);
    }

    /**
     * Remove a single instance of the specified peer from this object.
     *
     * @param peer the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException if the specified peer is null
     */
    @Override
    public boolean removePeer(final Thing42orNull<K, D> peer)
        throws NullPointerException {
        if (peer == null) {
            throw new NullPointerException();
        }
        return this.peers.remove(peer);
    }

    /**
     * Modify the data of this Thing42.
     *
     * @param newData the updated data for this object
     */
    @Override
    public void setData(final D newData) {
        this.data = newData;
    }
}
