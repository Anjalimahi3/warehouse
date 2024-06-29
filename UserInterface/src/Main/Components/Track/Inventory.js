import React from 'react';
import { Pie } from '@ant-design/plots';

const Inventory = () => {
  const data = [
    {
      type: 'Cement',
      value: 27,
    },
    {
      type: 'Iron',
      value: 25,
    },
    {
      type: 'Electronic',
      value: 18,
    },
    {
      type: 'Pipes',
      value: 15,
    }
  ];
  const config = {
    appendPadding: 10,
    data,
    angleField: 'value',
    colorField: 'type',
    radius: 1,
    innerRadius: 0.6,
    label: {
      type: 'inner',
      offset: '-50%',
      content: '{value}',
      style: {
        textAlign: 'center',
        fontSize: 14,
      },
    },
    interactions: [
      {
        type: 'element-selected',
      },
      {
        type: 'element-active',
      },
    ],
    statistic: {
      title: false,
      content: {
        style: {
          whiteSpace: 'pre-wrap',
          overflow: 'hidden',
          textOverflow: 'ellipsis',
        },
        content: 'AGS',
      },
    },
  };
  return <Pie {...config} width={500} autoFit={false} style={{marginLeft:'20px'}}/>;
};

export default Inventory;