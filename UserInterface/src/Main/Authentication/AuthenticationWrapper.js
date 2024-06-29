import React, { Fragment} from "react";
import Style from './Authentication.module.scss';

const AuthenticationWrapper=({children})=>{

    return(
        <Fragment>
            <div className={Style.authWrapper}>
                {children}
            </div>
        </Fragment>
    )
}
export default AuthenticationWrapper;