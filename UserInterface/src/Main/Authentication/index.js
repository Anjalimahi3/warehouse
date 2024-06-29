import React, { Fragment } from "react";
import AuthenticationWrapper from "./AuthenticationWrapper";
import SignIn from "./signIn";

const Login=()=>{

    return(
        <Fragment>
            <AuthenticationWrapper>
                <SignIn/>
            </AuthenticationWrapper>
        </Fragment>
    )

}
export default Login;