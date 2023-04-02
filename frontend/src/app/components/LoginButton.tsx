import { Button } from 'antd'
import React from 'react'

const LoginButton: React.FC = () => {

    const login = () => {
        window.location.href = window.location.origin + '/loan-api/login'
	}

    return (
        <div>
            <Button type={'primary'} onClick={() => login()}>Login</Button>
        </div>
    )
}

export default LoginButton
