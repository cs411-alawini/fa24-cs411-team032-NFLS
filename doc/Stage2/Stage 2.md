# Stage 2

# UML Diagram

![UML Diagram](./UML%20Diagram%20Revised.png)

# Entities

1. **User**: Represents individual accounts in the web app. Attributes include:`FirstName` ,
    
    `LastName`,`Email`,`PhoneNumber`
    
2. **Product**: Represents products available for purchase, such as exercise essentials and personal training lessons. Attributes include:`ProductName` , `Price`
3. **Purchase**: Captures transactions made by users, tracking the price and quantity of each purchase. For example, buying two trainers is considered as one purchase with a quantity of two. This entity has attributes:`Price` , `Quantity`
4. **Event**: Represents events hosted or attended by users. Events can be casual run sessions or professional marathons, with attributes like:`Date`, `Location`
5. **RunSessionData**: Stores data on individual running sessions, tracking user progress. Attributes include:`SessionDistance`, `StartTime` , `EndTime`

# Relationships and Cardinality

- **User - User (Make Friends)**: Many-to-many relationship with itself. Users can have multiple friends, and each friend is also a user.
- **User - Purchase (Shopping)**: One-to-many relationship. A user can make multiple purchases, but each purchase is associated with only one user.
- **User - Event (Host)**: Many-to-many relationship. Users can host multiple events, and an event can be hosted by multiple users.
- **User - RunSessionData (UserRunningData)**: One-to-many relationship. Each RunSessionData can have one associated User who initiates it, and each user can have multiple RunSessionData that tracks their running activities.
- **Product - Purchase (ProductId)**: Many-to-one relationship. Each purchase can be associated with only one product, but each product can appear in multiple purchases.
- **Event - RunSessionData (Event)**: Many-to-one relationship. Each running session is associated with a single event, but an event can encompass multiple running sessions.

# Normalization Mapping

now we have

- User(UserId, FirstName, LastName, Email, PhoneNumber)
- MakeFriends(FriendshipId, UserId, FriendUserId, StartDate, FriendshipLevel)
- Event(EventID, Date, Location)
- Host(UserId, EventId)
- RunSessionData(RunSessionId, UserId, Session Distance, StartTime, EndTime, EventId)
- Purchase(PurchaseId, PurchasePrice, Quantity, UserId, ProductId)
- Product(ProductId, ProductName, ProductPrice)

### Attribute Mapping

- User: (A, B, C, D, E)
- MakeFriends: (F, A, A, G, H)
- Event: (I, J, K)
- Host: (A, I)
- RunSessionData: (L, A, M, N, O, I)
- Purchase: (P, Q, R, A, S)
- Product: (S, T, U)

### Functional Dependencies

- `A` → `B`, `C`, `D`, `E`
- `F` → `A`, `G`, `H`
- `I` → `J`, `K`
- `L` → `A`, `M`, `N`, `O`, `I`
- `P` → `Q`, `R`, `A`, `S`
- `S` → `T`, `U`

## Decomposition

**Z1**: (A, B, C, D, E), **Z2**: (A, G, H), **Z3**: (I, J, K), **Z4**: (A, I), **Z5**: (L, A, M, N, O, I), **Z6**: (P, Q, R, A, S), **Z7**: (S, T, U), **Z8**: (F, L, P, A, I, S)

# Relational Schema

- **User**(UserId: INT [PK], FirstName: VARCHAR(100), LastName: VARCHAR(100), Email: VARCHAR(255) UNIQUE, PhoneNumber: VARCHAR(20))
- **MakeFriends**(FriendshipId: INT [PK], UserId: INT [FK to User.UserId], FriendUserId: INT [FK to User.UserId], StartDate: DATE, FriendshipLevel: VARCHAR(50))
- **Event**(EventID: INT [PK], Date: DATE, Location: VARCHAR(255))
- **Host**(UserId: INT [FK to User.UserId], EventId: INT [FK to Event.EventID])
- **RunSessionData**(RunSessionId: INT [PK], UserId: INT [FK to User.UserId], SessionDistance: FLOAT, StartTime: DATETIME, EndTime: DATETIME, EventId: INT [FK to Event.EventID])
- **Purchase**(PurchaseId: INT [PK], PurchasePrice: DECIMAL(10, 2), Quantity: INT, UserId: INT [FK to User.UserId], ProductId: INT [FK to Product.ProductId])
- **Product**(ProductId: INT [PK], ProductName: VARCHAR(255), ProductPrice: DECIMAL(10, 2))
