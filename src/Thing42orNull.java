import java.util.Collection;
import java.util.List;

/**
 * Per the definition specified in the preliminary programming assignment;
 * This interface specifies the required methods for {@code Thing42} objects. 
 * <p>Its name reflects that the value of a variable of this type 
 * may be a reference to a {@code Thing42} object or the value may be null.<br>
 * 
 * <p>In addition to the {@code Key} and {@code Data} attributes, the 
 * {@code Thing42orNull} interface specifies that a {@code Thing42} object has 
 * an integer-valued attribute known as its {@code level}, an unordered 
 * {@code java.util.Collection} of {@code Thing42} objects known as its 
 * {@code peers} as well as an ordered {@code java.util.Collection} of 
 * {@code Thing42} objects known as its {@code pool} that allows duplicates. 
 * <br>
 * 
 * <p>The peers and pool {@code java.util.Collection}'s do allow for the 
 * storing of duplicate Thing42 objects. <br>
 * 
 * <p>The {@code Thing42orNull} interface places extra specifications, 
 * in addition to those specified in the Object class, on the contracts 
 * of the {@code equals} and {@code hashCode} methods. <br>
 * 
 * @author Group 3 - MW - From Jody Paul's API
 * @version CS4250 Fall 2014 - API - (31 August 2014)
 * 
 * @param <K> generic attribute known as its {@code Key}
 * @param <D> generic mutable attribute known as its {@code Data}
 */
public interface Thing42orNull <K,D>{    
    
    /**
     * Add a peer to this object.
     * 
     * @param newPeer the peer to be added
     * @throws NullPointerException if the specified peer is null 
     */
    void addPeer(Thing42orNull<K,D> newPeer); 
    
    /**
     * Append a member to the pool of this object.
     * 
     * @param newMember the object to be appended to the pool
     * @throws NullPointerException if the specified item is null
     */
    void appendToPool(Thing42orNull<K,D> newMember); 
    
    /**
     * Access the data of this Thing42.
     * 
     * @return the data of this object
     */
    D getData();
    
    /**
     * Access the key of this Thing42.
     * 
     * @return the key of this object
     */
    K getKey();
    
    /**
     * Access the level of this Thing42.
     * 
     * @return the level of this object
     */
    long getLevel();
    
    /**
     * Access a peer matching the specified key.
     * @param key the search key
     * @return any peer known by this object that matches the given key; 
     * null if no match
     */
    Thing42orNull<K,D> getOnePeer(K key);
    
    /**
     * Access all peers.
     * @return all peers known by this object; 
     * if no peers then returns a collection with size() == 0.
     */
    Collection<Thing42orNull<K,D>> getPeersAsCollection();
    
    /**
     * Access all peers matching the specified key.
     * @param key the search key
     * @return all peers known by this object that match the given key; 
     * if no peer matches then returns a collection with size() == 0.
     */
    Collection<Thing42orNull<K,D>> getPeersAsCollection(K key);
    
    /**
     * Access all members of the pool.
     * @return all members of the pool known by this object; 
     * if no members then returns a list with size() == 0.
     */
    List<Thing42orNull<K,D>> getPoolAsList();
    
    /**
     * Remove a single instance of the specified object from this object's pool.
     * 
     * @param member the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException  if the specified parameter is null
     */
    boolean removeFromPool(Thing42orNull<K,D> member);
    
    /**
     * Remove a single instance of the specified peer from this object.
     * 
     * @param peer the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException if the specified peer is null
     */
    boolean removePeer(Thing42orNull<K,D> peer);
    
    /**
     * Modify the data of this Thing42.
     * 
     * @param newData the updated data for this object
     */
    void setData(D newData);
    
    /**
     * <p>Compare the equality of two Thing42 objects to ensure they have the same
     * Key, Data, level, pool collection, and peers collection. 
     * Since K and D are generics equals must also compare K and D of both objects to make sure they are the same type
     *
     * @param otherThing the second Thing42 object to be compared with this Thing42 object
     * @return true if the two objects are equal	 
     */
    @Override
    boolean equals(Object obj);
    
    /**
     * Returns the hash code value for this Thing42 object
     *
     * @return hashCode int for this Thing42 object
     */
    @Override
    int hashCode();
}
