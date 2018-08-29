package com.yunlongstudio.algorithm.hash;

import java.util.*;


public class MyHashtable<K,V> implements java.io.Serializable {

    /**
     * The hash table data.
     */
    private transient Entry[] table;

    /**
     * The total number of entries in the hash table.
     */
    private transient int count;

    /**
     * The table is rehashed when its size exceeds this threshold.  (The
     * value of this field is (int)(capacity * loadFactor).)
     *
     * @serial
     */
    private int threshold;

    /**
     * The load factor for the hashtable.
     *
     * @serial
     */
    private float loadFactor;

    /**
     * The number of times this Hashtable has been structurally modified
     * Structural modifications are those that change the number of entries in
     * the Hashtable or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the Hashtable fail-fast.  (See ConcurrentModificationException).
     */
    private transient int modCount = 0;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1421746759512286392L;

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        the load factor of the hashtable.
     * @exception  IllegalArgumentException  if the initial capacity is less
     *             than zero, or if the load factor is nonpositive.
     */
    public MyHashtable(int initialCapacity, float loadFactor) {
	if (initialCapacity < 0)
	    throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);

        if (initialCapacity==0)
            initialCapacity = 1;
	this.loadFactor = loadFactor;
	table = new Entry[initialCapacity];
	threshold = (int)(initialCapacity * loadFactor);
    }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor (0.75).
     *
     * @param     initialCapacity   the initial capacity of the hashtable.
     * @exception IllegalArgumentException if the initial capacity is less
     *              than zero.
     */
    public MyHashtable(int initialCapacity) {
	this(initialCapacity, 0.75f);
    }

    /**
     * Constructs a new, empty hashtable with a default initial capacity (11)
     * and load factor (0.75).
     */
    public MyHashtable() {
	this(11, 0.75f);
    }

    /**
     * Constructs a new hashtable with the same mappings as the given
     * Map.  The hashtable is created with an initial capacity sufficient to
     * hold the mappings in the given Map and a default load factor (0.75).
     *
     * @param t the map whose mappings are to be placed in this map.
     * @throws NullPointerException if the specified map is null.
     * @since   1.2
     */
    public MyHashtable(Map<? extends K, ? extends V> t) {
	this(Math.max(2*t.size(), 11), 0.75f);
	putAll(t);
    }

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return  the number of keys in this hashtable.
     */
    public synchronized int size() {
	return count;
    }

    /**
     * Tests if this hashtable maps no keys to values.
     *
     * @return  <code>true</code> if this hashtable maps no keys to values;
     *          <code>false</code> otherwise.
     */
    public synchronized boolean isEmpty() {
	return count == 0;
    }


    /**
     * Tests if some key maps into the specified value in this hashtable.
     * This operation is more expensive than the {@link #containsKey
     * containsKey} method.
     *
     * <p>Note that this method is identical in functionality to
     * {@link #containsValue containsValue}, (which is part of the
     * {@link Map} interface in the collections framework).
     *
     * @param      value   a value to search for
     * @return     <code>true</code> if and only if some key maps to the
     *             <code>value</code> argument in this hashtable as
     *             determined by the <tt>equals</tt> method;
     *             <code>false</code> otherwise.
     * @exception  NullPointerException  if the value is <code>null</code>
     */
    public synchronized boolean contains(Object value) {
	if (value == null) {
	    throw new NullPointerException();
	}

	Entry tab[] = table;
	for (int i = tab.length ; i-- > 0 ;) {
	    for (Entry<K,V> e = tab[i] ; e != null ; e = e.next) {
		if (e.value.equals(value)) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Returns true if this hashtable maps one or more keys to this value.
     *
     * <p>Note that this method is identical in functionality to {@link
     * #contains contains} (which predates the {@link Map} interface).
     *
     * @param value value whose presence in this hashtable is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     * @throws NullPointerException  if the value is <code>null</code>
     * @since 1.2
     */
    public boolean containsValue(Object value) {
	return contains(value);
    }

    /**
     * Tests if the specified object is a key in this hashtable.
     *
     * @param   key   possible key
     * @return  <code>true</code> if and only if the specified object
     *          is a key in this hashtable, as determined by the
     *          <tt>equals</tt> method; <code>false</code> otherwise.
     * @throws  NullPointerException  if the key is <code>null</code>
     * @see     #contains(Object)
     */
    public synchronized boolean containsKey(Object key) {
	Entry tab[] = table;
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
	    if ((e.hash == hash) && e.key.equals(key)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key.equals(k))},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     * @see     #put(Object, Object)
     */
    public synchronized V get(Object key) {
	Entry tab[] = table;
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
	    if ((e.hash == hash) && e.key.equals(key)) {
		return e.value;
	    }
	}
	return null;
    }

    /**
     * Increases the capacity of and internally reorganizes this
     * hashtable, in order to accommodate and access its entries more
     * efficiently.  This method is called automatically when the
     * number of keys in the hashtable exceeds this hashtable's capacity
     * and load factor.
     */
    protected void rehash() {
	int oldCapacity = table.length;
	Entry[] oldMap = table;

	int newCapacity = oldCapacity * 2 + 1;
	Entry[] newMap = new Entry[newCapacity];

	modCount++;
	threshold = (int)(newCapacity * loadFactor);
	table = newMap;

	for (int i = oldCapacity ; i-- > 0 ;) {
	    for (Entry<K,V> old = oldMap[i] ; old != null ; ) {
		Entry<K,V> e = old;
		old = old.next;

		int index = (e.hash & 0x7FFFFFFF) % newCapacity;
		e.next = newMap[index];
		newMap[index] = e;
	    }
	}
    }

    /**
     * Maps the specified <code>key</code> to the specified
     * <code>value</code> in this hashtable. Neither the key nor the
     * value can be <code>null</code>. <p>
     *
     * The value can be retrieved by calling the <code>get</code> method
     * with a key that is equal to the original key.
     *
     * @param      key     the hashtable key
     * @param      value   the value
     * @return     the previous value of the specified key in this hashtable,
     *             or <code>null</code> if it did not have one
     * @exception  NullPointerException  if the key or value is
     *               <code>null</code>
     * @see     Object#equals(Object)
     * @see     #get(Object)
     */
    public synchronized V put(K key, V value) {
	// Make sure the value is not null
	if (value == null) {
	    throw new NullPointerException();
	}

	// Makes sure the key is not already in the hashtable.
	Entry tab[] = table;
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
	    if ((e.hash == hash) && e.key.equals(key)) {
		V old = e.value;
		e.value = value;
		return old;
	    }
	}

	modCount++;
	if (count >= threshold) {
	    // Rehash the table if the threshold is exceeded
	    rehash();

            tab = table;
            index = (hash & 0x7FFFFFFF) % tab.length;
	}

	// Creates the new entry.
	Entry<K,V> e = tab[index];
	tab[index] = new Entry<K,V>(hash, key, value, e);
	count++;
	return null;
    }

    /**
     * Removes the key (and its corresponding value) from this
     * hashtable. This method does nothing if the key is not in the hashtable.
     *
     * @param   key   the key that needs to be removed
     * @return  the value to which the key had been mapped in this hashtable,
     *          or <code>null</code> if the key did not have a mapping
     * @throws  NullPointerException  if the key is <code>null</code>
     */
    public synchronized V remove(Object key) {
	Entry tab[] = table;
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	for (Entry<K,V> e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
	    if ((e.hash == hash) && e.key.equals(key)) {
		modCount++;
		if (prev != null) {
		    prev.next = e.next;
		} else {
		    tab[index] = e.next;
		}
		count--;
		V oldValue = e.value;
		e.value = null;
		return oldValue;
	    }
	}
	return null;
    }

    /**
     * Copies all of the mappings from the specified map to this hashtable.
     * These mappings will replace any mappings that this hashtable had for any
     * of the keys currently in the specified map.
     *
     * @param t mappings to be stored in this map
     * @throws NullPointerException if the specified map is null
     * @since 1.2
     */
    public synchronized void putAll(Map<? extends K, ? extends V> t) {
        for (Map.Entry<? extends K, ? extends V> e : t.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * Clears this hashtable so that it contains no keys.
     */
    public synchronized void clear() {
	Entry tab[] = table;
	modCount++;
	for (int index = tab.length; --index >= 0; )
	    tab[index] = null;
	count = 0;
    }

    /**
     * Hashtable collision list.
     */
    private static class Entry<K,V> implements Map.Entry<K,V> {
	int hash;
	K key;
	V value;
	Entry<K,V> next;

	protected Entry(int hash, K key, V value, Entry<K,V> next) {
	    this.hash = hash;
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}

	protected Object clone() {
	    return new Entry<K,V>(hash, key, value,
				  (next==null ? null : (Entry<K,V>) next.clone()));
	}

	// Map.Entry Ops

	public K getKey() {
	    return key;
	}

	public V getValue() {
	    return value;
	}

	public V setValue(V value) {
	    if (value == null)
		throw new NullPointerException();

	    V oldValue = this.value;
	    this.value = value;
	    return oldValue;
	}

	public boolean equals(Object o) {
	    if (!(o instanceof Map.Entry))
		return false;
	    Map.Entry e = (Map.Entry)o;

	    return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
	       (value==null ? e.getValue()==null : value.equals(e.getValue()));
	}

	public int hashCode() {
	    return hash ^ (value==null ? 0 : value.hashCode());
	}

    }

}
