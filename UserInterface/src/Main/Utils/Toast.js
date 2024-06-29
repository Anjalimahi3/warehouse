import { toast } from 'react-toastify';

export const ToastSuccess=(data)=>{
    return(
        toast.success(data, {
            position: "top-right",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            theme: "dark",
            })
    )
}

export const ToastWarn=(data)=>{
    return(
        toast.warn(data, {
            position: "top-right",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "dark",
          })
    )
}