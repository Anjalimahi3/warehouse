import React from 'react';
import { Column } from '@ant-design/plots';

 const DemoColumn = () => {
  const data =[
	{product:"cement", type:"stockvalue", value:200},
	{product:"cement", type:"salesValue", value:200},
	{product:"cement", type:"stockvalue", value:500},
	{product:"cement", type:"salesValue", value:200},
	{product:"cement", type:"stockvalue", value:2000},
	{product:"cement", type:"salesValue", value:900},
	{product:"cement", type:"salesValue", value:200},
	{product:"iron", type:"stockvalue", value:3000},
	{product:"iron", type:"sales", value:2000},
	{product:"iron", type:"stockvalue", value:6000},
	{product:"iron", type:"sales", value:1000},
	{product:"Electronic", type:"stockvalue", value:3000},
	{product:"Electronic", type:"sales", value:5670},
	{product:"Electronic", type:"stockvalue", value:345},
	{product:"Electronic", type:"sales", value:4560},
	{product:"Pipes", type:"stockvalue", value:780},
	{product:"Pipes", type:"sales", value:670},
	{product:"Pipes", type:"stockvalue", value:543},
	{product:"Pipes", type:"sales", value:4560}
];

const sortinOrder=(arg)=>{
    const Sortedval = arg.sort((present, upcoming)=>present.value >upcoming.value);
    console.log(Sortedval);
    return Sortedval
    
}
 
  const config = {
    data:sortinOrder(data),
    xField: 'product',
    yField: 'value',
    seriesField: 'type',
    isGroup: true,
    columnStyle: {
      radius: [10, 10, 0, 0],
    },
    style: {
        fill: 'red',
        fillOpacity: 0.5,
        stroke: 'black',
        lineWidth: 1,
        lineDash: [4, 5],
        strokeOpacity: 0.7,
        shadowColor: 'black',
        shadowBlur: 10,
        shadowOffsetX: 5,
        shadowOffsetY: 5,
        cursor: 'pointer'
      }
  };

  return <div className='m-5'><Column {...config} maxColumnWidth={13} dodgePadding={5} /></div>;
};

export default DemoColumn;