import React from 'react';

export const LabelInput=({heading,type, placeholder, id, className})=>{
    return(
    <div className={`form-group ${className}`}>
    <label for={id}>{heading}</label>
    <input type={type} class="form-control" id={id} placeholder={placeholder}/>
  </div>)
}
export const Row=({children, ...props})=><div className='row' {...props}>{children}</div>
export const Col=({children})=><div className='col'>{children}</div>
