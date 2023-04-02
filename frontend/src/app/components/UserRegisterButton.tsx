import { Button } from 'antd'
import React from 'react'

const UserRegisterButton: React.FC = () => {

    const register = () => {
        window.location.href = window.location.origin + '/loan-api/user/register'
	}

    return (
        <div>
            <Button type={'primary'} onClick={() => register()}>Register new user</Button>
        </div>
    )
}

export default UserRegisterButton
