export const sortingOrder = (loop,Listvalues, setSorting, setValues, sortingKey) => {
  const Numbervalues = loop.type==="number" ? true : false
    const sortingResult = [...Listvalues].sort(function (x, y) {
      let a = Numbervalues ? x?.[loop.key]?.toString()?.toUpperCase() : x?.[loop.key]
      let b = Numbervalues ? y?.[loop.key]?.toString()?.toUpperCase() : y?.[loop.key]
      if (loop.key === sortingKey) {
        setSorting(null);
        return a == b ? 0 : b > a ? -1 : 1;
      }
      setSorting(loop.key);
      return a == b ? 0 : b > a ? 1 : -1;
    });
    
    setValues(sortingResult);
  };

export const APISortingOrder=(data)=>{
  return data.sort((loopFirst, loopSecond) => {
    return (
      new Date(loopSecond.purchaseDateTime) -
      new Date(loopFirst.purchaseDateTime));
  });
}