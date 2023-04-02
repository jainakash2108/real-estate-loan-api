import React, {useState} from "react";
import AdminRegistrationForm from "../components/AdminRegistrationForm";
import {Col, Row} from "antd";
import AdviserPageButton from "../components/AdviserPageButton";
import ErrorBanner from "../components/ErrorBanner";
import SuccessBanner from "../components/SuccessBanner";

const AdminRegistrationPage: React.FC = () => {

    const [error, setError] = useState<string>('')
    const [success, setSuccess] = useState<string>('')

    return (
        <div>
            <main>
                <Row justify="center" style={{marginTop: '1rem'}}>
                    <h1>Admin registration</h1>
                    <Col span={24}>
                        <Row justify="center" style={{marginTop: '2rem'}}>
                            <AdviserPageButton/>
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            {error !== '' && (<ErrorBanner message={error}/>)}
                            {success !== '' && (<SuccessBanner message={success}/>)}
                        </Row>
                        <Row justify="center" style={{marginTop: '2rem'}}>
                            <AdminRegistrationForm setError={setError} setSuccess={setSuccess}/>
                        </Row>
                    </Col>
                </Row>
            </main>
        </div>
    );
}

export default AdminRegistrationPage