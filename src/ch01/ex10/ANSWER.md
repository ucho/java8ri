Collectionsのメソッド|追加するインタフェース|default or static?
-------------------+-----------------+-----------------------------
static <T> boolean addAll(Collection<? super T> c, T... elements) | Collection | default
static <T> Queue<T> asLifoQueue(Deque<T> deque) | Deque | default
static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) | List | default
static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) | List | default
static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type) | Collection | default
static <E> List<E> checkedList(List<E> list, Class<E> type) | List | default
static <K,V> Map<K,V> checkedMap(Map<K,V> m, Class<K> keyType, Class<V> valueType) | Map | default
static <K,V> NavigableMap<K,V> checkedNavigableMap(NavigableMap<K,V> m, Class<K> keyType, Class<V> valueType) | NavigableMap | default
static <E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type) | NavigableSet | default
static <E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type) | Queue | default
static <E> Set<E> checkedSet(Set<E> s, Class<E> type) | Set | default
static <K,V> SortedMap<K,V> checkedSortedMap(SortedMap<K,V> m, Class<K> keyType, Class<V> valueType) | SortedMap | default
static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type) | SortedSet | default
static <T> void copy(List<? super T> dest, List<? extends T> src) | List | static
static boolean disjoint(Collection<?> c1, Collection<?> c2) | Collection | static
static <T> Enumeration<T> emptyEnumeration() | Enumeration | static
static <T> Iterator<T> emptyIterator() | Iterator | static
static <T> List<T> emptyList() | List | static
static <T> ListIterator<T> emptyListIterator() | ListIterator | static
static <K,V> Map<K,V> emptyMap() | Map | static
static <K,V> NavigableMap<K,V> emptyNavigableMap() | NavigableMap | static
static <E> NavigableSet<E> emptyNavigableSet() | NavigableSet | static
static <T> Set<T> emptySet() | Set | static
static <K,V> SortedMap<K,V> emptySortedMap() | SortedMap | static
static <E> SortedSet<E> emptySortedSet() | SortedSet | static
static <T> Enumeration<T> enumeration(Collection<T> c) | Collection | default
static <T> void fill(List<? super T> list, T obj) | List | default
static int frequency(Collection<?> c, Object o) | Collection | default
static int indexOfSubList(List<?> source, List<?> target) | List | default
static int lastIndexOfSubList(List<?> source, List<?> target) | List | default
static <T> ArrayList<T> list(Enumeration<T> e) | Enumeration | default
static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) | Collection | default
static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) | Collection | default
static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) | Collection | default
static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) | Collection | default
static <T> List<T> nCopies(int n, T o) | List | static
static <E> Set<E> newSetFromMap(Map<E,Boolean> map) | Map | default
static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) | List | default
static void reverse(List<?> list) | List | default
static <T> Comparator<T> reverseOrder() | Comparator | static
static <T> Comparator<T> reverseOrder(Comparator<T> cmp) | Comparator | default
static void rotate(List<?> list, int distance) | List | default
static void shuffle(List<?> list) | List | default
static void shuffle(List<?> list, Random rnd) | List | default
static <T> Set<T> singleton(T o) | Set | static
static <T> List<T> singletonList(T o) | List | static
static <K,V> Map<K,V> singletonMap(K key, V value) | Map | static
static <T extends Comparable<? super T>> void sort(List<T> list) | List | default
static <T> void sort(List<T> list, Comparator<? super T> c) | List | default
static void swap(List<?> list, int i, int j) | List | default
static <T> Collection<T> synchronizedCollection(Collection<T> c) | Collection | default
static <T> List<T> synchronizedList(List<T> list) | List | default
static <K,V> Map<K,V> synchronizedMap(Map<K,V> m) | Map | default
static <K,V> NavigableMap<K,V> synchronizedNavigableMap(NavigableMap<K,V> m) | NavigableMap | default
static <T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s) | NavigableSet | default
static <T> Set<T> synchronizedSet(Set<T> s) | Set | default
static <K,V> SortedMap<K,V> synchronizedSortedMap(SortedMap<K,V> m) | SortedMap | default
static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s) | SortedSet | default
static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) | Collection | default
static <T> List<T> unmodifiableList(List<? extends T> list) | List | default
static <K,V> Map<K,V> unmodifiableMap(Map<? extends K,? extends V> m) | Map | default
static <K,V> NavigableMap<K,V> unmodifiableNavigableMap(NavigableMap<K,? extends V> m) | NavigableMap | default
static <T> NavigableSet<T> unmodifiableNavigableSet(NavigableSet<T> s) | NavigableSet | default
static <T> Set<T> unmodifiableSet(Set<? extends T> s) | Set | default
static <K,V> SortedMap<K,V> unmodifiableSortedMap(SortedMap<K,? extends V> m) | SortedMap | default
static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) | SortedSet | default