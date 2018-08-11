-- June 16 M:N Example books and authors

drop database if exists June16Author;
create database if not exists June16Author;
use June16Author;


create table book
(
	bookID		smallint		primary key,
	bookTitle	varchar(35),
	bookYearPub	year
);


insert into book values
	(101,'A confederacy of Dunces', 1993),
	(102,'Black Elk Speaks', 1997),
	(103,"Hell's Angels", 1966),
	(104,'Letter to a Young Poet', 1934),
	(105,'Nonconformity',1997),
	(121,'Postcards',1992),
	(136,'The Shipping News',1993),
	(140,'Brokeback Mountain',1997)
	;
	

create table author
(
	authorID	smallint		primary key,
	authorFName	varchar(15),
	authorLName	varchar(15)
);

insert into author values
	(22, 'John','Toole'),
	(44,  'Black', 'Elk'),
	(47, 'John', 'Neihardt'),
	(76, 'Hunter','Thompson'),
	(82, 'Rainer','Rilke'),
	(92,'Nelson','Algren'),
	(95,'Annie','Proulx');

create table bookAuthorTable
(
	bookID 	smallInt,
	authorID smallInt,
		Primary Key(bookID, authorID),

		constraint book_fk_link
			foreign key (bookID)
			references book(bookID),

		constraint author_fk_link
			foreign key (authorID)
			references author(authorID)
);

insert into bookAuthorTable values
	(101,22),
	(102,44),
	(102,47),
	(103,76),
	(104,82),
	(105,92),
	(121,95),
	(136,92),
	(140,92);
