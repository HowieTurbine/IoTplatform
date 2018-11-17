use Datasets
go

-- create table - drop table MyTable
CREATE TABLE MyTable (Product varchar(10),ThisQuarter varchar(5),Region varchar(10),Sales real)
go


-- add records
INSERT INTO MyTable VALUES ('A','Q1','Europe',10)
INSERT INTO MyTable VALUES ('A','Q1','Europe',12)
INSERT INTO MyTable VALUES ('A','Q1','America',20)
INSERT INTO MyTable VALUES ('A','Q1','America',18)
INSERT INTO MyTable VALUES ('A','Q2','Europe',20)
INSERT INTO MyTable VALUES ('A','Q2','Europe',23)
INSERT INTO MyTable VALUES ('A','Q2','Europe',24)
INSERT INTO MyTable VALUES ('A','Q2','America',50)
INSERT INTO MyTable VALUES ('A','Q2','America',48)
INSERT INTO MyTable VALUES ('A','Q3','America',20)
INSERT INTO MyTable VALUES ('A','Q4','Europe',10)
INSERT INTO MyTable VALUES ('A','Q4','Europe',8)
INSERT INTO MyTable VALUES ('A','Q4','Europe',13)
INSERT INTO MyTable VALUES ('A','Q4','Europe',7)
INSERT INTO MyTable VALUES ('A','Q4','America',30)
INSERT INTO MyTable VALUES ('B','Q1','Europe',40)
INSERT INTO MyTable VALUES ('B','Q1','Europe',30)
INSERT INTO MyTable VALUES ('B','Q1','Europe',20)
INSERT INTO MyTable VALUES ('B','Q1','Europe',50)
INSERT INTO MyTable VALUES ('B','Q1','America',60)
INSERT INTO MyTable VALUES ('B','Q1','America',50)
INSERT INTO MyTable VALUES ('B','Q1','America',40)
INSERT INTO MyTable VALUES ('B','Q2','Europe',20)
INSERT INTO MyTable VALUES ('B','Q2','Europe',25)
INSERT INTO MyTable VALUES ('B','Q2','America',10)
INSERT INTO MyTable VALUES ('B','Q2','America',11)
INSERT INTO MyTable VALUES ('B','Q2','America',16)
INSERT INTO MyTable VALUES ('B','Q2','America',13)
INSERT INTO MyTable VALUES ('B','Q2','America',10)
INSERT INTO MyTable VALUES ('B','Q3','America',20)
INSERT INTO MyTable VALUES ('B','Q3','America',28)
INSERT INTO MyTable VALUES ('B','Q4','Europe',10)
INSERT INTO MyTable VALUES ('B','Q4','Europe',5)
INSERT INTO MyTable VALUES ('B','Q4','Europe',5)
INSERT INTO MyTable VALUES ('B','Q4','America',40)
INSERT INTO MyTable VALUES ('B','Q4','America',20)
INSERT INTO MyTable VALUES ('B','Q4','America',50)
INSERT INTO MyTable VALUES ('B','Q4','America',30)
go

 
-- pivot query
select 
	* 
from 
	MyTable PIVOT(SUM(Sales) FOR ThisQuarter IN (Q1,Q2,Q3,Q4)) AS P


-- pivot query
select
	Region, Q1, Q2, Q3, Q4
from
	MyTable PIVOT(SUM(Sales) FOR ThisQuarter IN (Q1,Q2,Q3,Q4)) AS P


	
-- pivot query with aggregate
SELECT Product, Region, Q1, Q2, Q3, Q4
FROM   
(SELECT Product, Region, ThisQuarter, Sales FROM MyTable) AS p  
PIVOT  
(sum(Sales) FOR ThisQuarter IN (Q1,Q2,Q3,Q4)) AS pvt  


-- group by with cube
SELECT ThisQuarter, Region, Product, SUM(Sales)as TotalSales, GROUPING(ThisQuarter) AS 'Grouping' 
FROM MyTable
GROUP BY ThisQuarter, Region, Product with cube
ORDER BY 1,2,3


-- group by with rollup
SELECT ThisQuarter, Region, Product, SUM(Sales)as TotalSales, GROUPING(ThisQuarter) AS 'Grouping' 
FROM MyTable
GROUP BY ThisQuarter, Region, Product with rollup
ORDER BY 1,2,3


-- group by grouping sets
SELECT ThisQuarter, Region, SUM(Sales) as TotalSales
FROM MyTable
GROUP BY GROUPING SETS ((ThisQuarter), (Region))
ORDER BY 1,2

--
SELECT ThisQuarter, NULL as Region, SUM(Sales) as TotalSalesFROM MyTableGROUP BY ThisQuarter
UNION ALL
SELECT NULL, Region, SUM(Sales)as TotalSales FROM MyTableGROUP BY Region
ORDER BY 1,2


-- Ranking
SELECT 
	Product, Sales
	, RANK() OVER (ORDER BY Sales ASC) as RANK_SALES
	, DENSE_RANK() OVER (ORDER BY Sales ASC) as DENSE_RANK_SALES
	, PERCENT_RANK() OVER (ORDER BY Sales ASC) as PERC_RANK_SALES
	, CUME_DIST() OVER (ORDER BY Sales ASC) as CUM_DIST_SALES
FROM 
	MyTable
ORDER BY 
	RANK_SALES ASC


-- Windowing
SELECT 
	ThisQuarter, Region, Sales
	, AVG(Sales) OVER (PARTITION BY Region ORDER BY ThisQuarter) AS Sales_Avg
FROM 
	MyTable
ORDER BY 
	Region, ThisQuarter, Sales_Avg


-- Windowing
SELECT 
	ThisQuarter, Region, Sales
	, AVG(Sales) OVER (PARTITION BY Region ORDER BY ThisQuarter ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS Sales_Avg
FROM 
	MyTable
ORDER BY 
	Region, ThisQuarter, Sales_Avg





