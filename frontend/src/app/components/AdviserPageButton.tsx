import { Button } from 'antd'
import React from 'react'

const AdviserPageButton: React.FC = () => {

    const back = () => {
        window.location.href = window.location.origin + '/loan-api/adviser/page'
	}

    return (
        <div>
            <Button type={'primary'} onClick={() => back()}>Back to Adviser page</Button>
        </div>
    )
}

export default AdviserPageButton
