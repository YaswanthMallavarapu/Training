const stocks = [
    {symbol: 'NTPC' , price: 360, change: 2.5},
    {symbol: 'SBI' , price: 756, change: -0.5},
    {symbol: 'HDFC' , price: 970, change: -1.2},
    {symbol: 'APL APOLLO' , price: 240, change: 1.6}
]

//filtering of array
//? filter array of stocks which have positive returns
const positiveStocks=stocks.filter(stock=>stock.change > 0)

positiveStocks.forEach(stock=>console.log(stock));

//sorting of array
//never sort the original array as the order may require in other operations

console.log("=============================================");
const asc_stocks_based_on_price=[...stocks].sort((s1,s2)=>s1.price-s2.price)
asc_stocks_based_on_price.forEach(stock=>console.log(stock))
console.log("=============================================");
const desc_stocks_based_on_price=[...stocks].sort((s1,s2)=>s2.price-s1.price)
desc_stocks_based_on_price.forEach(stock=>console.log(stock))

console.log("=============================================");
const sname="SB"
const stockObj=stocks.find(stock=>stock.symbol==sname)
console.log(stockObj==undefined?"Stock not found":stockObj);