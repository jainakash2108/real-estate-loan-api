import React from 'react'
import { Button } from 'antd';

const LogoutButton: React.FC = () => {

    const logout = () => {
        window.location.href = window.location.origin + '/loan-api/logout'
	}

    return (
        <div>
            <Button type={'text'} onClick={() => logout()}>Logout</Button>
        </div>
    )
}

export default LogoutButton
