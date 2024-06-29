import { useState, useEffect } from 'react';

export const getWindowDimensions=()=> {
  const { innerWidth: width, innerHeight: height } = window;
  return {
    width,
    height
  };
}