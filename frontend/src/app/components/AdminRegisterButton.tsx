import React from 'react'
import { Button } from 'antd';

const AdminRegisterButton: React.FC = () => {

    const register = () => {
        window.location.href = window.location.origin + '/loan-api/admin/register'
	}

    return (
        <div>
            <Button type={'primary'} onClick={() => register()}>Create new admin user</Button>
        </div>
    )
}

export default AdminRegisterButton
