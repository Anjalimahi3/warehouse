import { useState } from "react";
import { axiosGET } from "../Axios";

export const selectDataValues = async()=>{
    const response = await axiosGET(process.env.REACT_APP_INVENTORY);
    if (response.status === 200) {
      const responseData = response.data;
      const FetchParticlarRecords = responseData.map((loopItem)=>{
        return {
          vendorName:loopItem.vendorName,
          productName:loopItem.productName,
          unit : loopItem.unit,
          piId : loopItem.piId
        }
      }) 
      const sortingOrder = FetchParticlarRecords.sort((a,b)=>(a.vendorName).localeCompare(b.vendorName))
      const FilesList = [];
      sortingOrder.map((looping, key)=>{
          const arrayContains = FilesList.map(loop=>loop.vendorName).indexOf(looping.vendorName)
          if(arrayContains >-1){
              const arrayVal = FilesList[arrayContains];
              const productContains = (arrayVal.product).map(loop=>loop.productName).indexOf(looping.productName);
                  if(productContains >-1){
                      arrayVal.product[productContains].unit.push({unit:looping.unit, piId:looping.piId })
                  } else{
                      arrayVal.product.push({productName:looping.productName, unit:[{unit:looping.unit, piId:looping.piId }]})
                  }
          } else{
                FilesList.push({
                  vendorName: looping.vendorName,
                  product:[{
                      productName :  looping.productName,
                      unit :[{unit:looping.unit, piId:looping.piId }]
                  }]
         })}
      });
      return FilesList;
    }
    else{
      return []
    }

}