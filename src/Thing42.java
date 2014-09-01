import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Implementation of all elements of the {@code Thing42orNull} interface. 
 * 
 * @author Group 3 - MW - From Jody Paul's API
 * @version CS4250 Fall 2014 - API - (31 August 2014)
 */
public class Thing42<K,D> implements Thing42orNull<K,D>{

    final private K key;
    final private long level;
    private D data;
    private Collection<Thing42orNull<K,D>> peers;
    private List<Thing42orNull<K,D>> pool;
    
    /**
     * Constructs an empty Thing42 with it's level set to 0.
     */
    public Thing42(){
        this.key = null;
        this.level = 0;
        this.data = null;
        this.peers = null;
        this.pool = null;
    }
    /**
     * Construct a Thing42
     * whose value is specified by the parameter.
     * @param key a generic attribute 
     * @param level integer-valued attribute
     * @param data generic mutable attribute
     */
    public Thing42(final K key, final long level, final D data){
        this.key = key;
        this.level = level;
        this.data = data;
        this.peers = new HashSet<Thing42orNull<K,D>>();
        this.pool = new ArrayList<Thing42orNull<K,D>>();
    }
    
    /**
     * Add a peer to this object.
     * 
     * @param newPeer the peer to be added
     * @throws NullPointerException if the specified peer is null 
     */
    public void addPeer(Thing42orNull<K,D> newPeer) {
        if (newPeer.equals(null)) { throw new NullPointerException(); }
        HashSet<Thing42orNull<K,D>> peerSet = new HashSet<Thing42orNull<K,D>>();        
        peerSet = (HashSet<Thing42orNull<K,D>>)this.getPeersAsCollection();
        peerSet.add(newPeer);
        this.peers = peerSet;
    }
    
    /**
     * Append a member to the pool of this object.
     * 
     * @param newMember the object to be appended to the pool
     * @throws NullPointerException if the specified item is null
     */
    public void appendToPool(Thing42orNull<K,D> newMember) {        
        if (newMember.equals(null)) { throw new NullPointerException(); }
        else{ this.pool.add(newMember); } 
    }
    
    /**
     * Access the data of this Thing42.
     * 
     * @return the data of this object
     */
    public D getData() {
        return this.data;
    }
    
    /**
     * Access the key of this Thing42.
     * 
     * @return the key of this object
     */
    public final K getKey() {
        return this.key;
    }

    /**
     * Access the level of this Thing42.
     * 
     * @return the level of this object
     */
    public final long getLevel() {
        return this.level;
    }
    
    /**
     * Access a peer matching the specified key.
     * @param key the search key
     * @return any peer known by this object that matches the given key; 
     * null if no match
     */
    public Thing42orNull<K,D> getOnePeer(K key) {
        if (this.isEmptyPeer()){return null;}
        Thing42orNull newThing42 = null;        
        for(Thing42orNull peer 
                : (HashSet<Thing42orNull<K,D>>)this.getPeersAsCollection(key)){
            if(peer.getKey().equals(key)){
                newThing42 = peer;
                break;
            }                    
        }           
        return newThing42;
    }

    /**
     * Access all peers.
     * @return all peers known by this object; 
     * if no peers then returns a collection with size() == 0.
     */
    public Collection getPeersAsCollection() {
         if (this.isEmptyPeer()){return new HashSet<Thing42orNull<K,D>>();}
         return this.peers;
    }

    /**
     * Access all peers matching the specified key.
     * @param key the search key
     * @return all peers known by this object that match the given key; 
     * if no peer matches then returns a collection with size() == 0.
     */
    public Collection getPeersAsCollection(K key) {
        if (this.isEmptyPeer()){return new HashSet<Thing42orNull<K,D>>();}
        Collection<Thing42orNull<K,D>> peerSet = new HashSet<Thing42orNull<K,D>>();
        for (Thing42orNull peer : this.peers){
            if(peer.getKey().equals(key)){peerSet.add(peer);}
        }
        return peerSet;
    }

    /**
     * Access all members of the pool.
     * @return all members of the pool known by this object; 
     * if no members then returns a list with size() == 0.
     */
    public List getPoolAsList() {
        if (this.isEmptyPool()){return new ArrayList<Thing42orNull<K,D>>();}
        return this.pool;
    }

    /**
     * Remove a single instance of the specified object from this object's pool.
     * 
     * @param member the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException if the specified parameter is null
     */
    public boolean removeFromPool(Thing42orNull<K,D> member) {
        if (member.equals(null)) { throw new NullPointerException(); }
        if (this.isEmptyPool()){return false;}
        List<Thing42orNull<K,D>> poolList = this.getPoolAsList();        
        if (poolList.remove(member)) {
            this.pool = poolList;
            return true;
        }
        return false;
    }

    /**
     * Remove a single instance of the specified peer from this object.
     * 
     * @param peer the peer to be removed
     * @return true if a peer was removed as a result of this call
     * @throws NullPointerException if the specified peer is null
     */
    public boolean removePeer(Thing42orNull<K,D> peer){
        if (peer.equals(null)) { throw new NullPointerException(); }
        if (this.isEmptyPeer()){return false;}
        //Collection<Thing42orNull> peerSet = new HashSet<Thing42orNull>();
        //peerSet = (HashSet<Thing42orNull>)this.getPeersAsCollection();
        Collection<Thing42orNull<K,D>> peerSet 
                = (HashSet<Thing42orNull<K,D>>)this.getPeersAsCollection();        
        if (peerSet.remove(peer)){
            this.peers = peerSet;
            return true;
        }
        return false;
    }

    /**
     * Modify the data of this Thing42.
     * 
     * @param newData the updated data for this object
     */
    public void setData(D newData) {
        this.data = newData;
    }   
    
    /**
     * Determine if Collection of peers is empty
     * @return <code>true</code> if the Collection of peers is empty;
     *         <code>false</code> if not
     */
    private boolean isEmptyPeer() {
        return this.peers.isEmpty();
    }
    
    /**
     * Determine if pool List is empty
     * @return <code>true</code> if the pool List is empty;
     *         <code>false</code> if not
     */
    private boolean isEmptyPool() {
        return this.pool.isEmpty();
    }    
    
    /**
     * <p>Compare the equality of two Thing42 objects to ensure they have the same
     * Key, Data, level, pool collection, and peers collection. Since K and D are generics
     * equals must also compare K and D of both objects to make sure they are the same type
     *
     * @param otherThing the second Thing42 object to be compared with this Thing42 object
     * @return true if the two objects are equal
     */
    public boolean equals(Thing42orNull<K,D> otherThing){
        return false;
    };
    
    /**
     * Returns the hash code value for this Thing42 object
     *
     * @return hashCode int for this Thing42 object
     */
    public int hashCode(){
        return 0;
    };
}
