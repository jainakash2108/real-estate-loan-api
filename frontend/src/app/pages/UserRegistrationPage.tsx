import {Col, Row} from "antd";
import React, {useState} from "react";
import LoginButton from "../components/LoginButton";
import UserRegistrationForm from "../components/UserRegistrationForm";
import ErrorBanner from "../components/ErrorBanner";
import SuccessBanner from "../components/SuccessBanner";

const UserRegistrationPage: React.FC = () => {

    const [error, setError] = useState<string>('')
    const [success, setSuccess] = useState<string>('')

    return (
        <div>
            <main>
                <Row justify="center" style={{marginTop: '2rem'}}>
                    <Col span={20}>
                        <Row justify="center" style={{marginTop: '2rem'}}>
                            <h1>User registration</h1>
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            <LoginButton/>
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            {error !== '' && (<ErrorBanner message={error}/>)}
                            {success !== '' && (<SuccessBanner message={success}/>)}
                        </Row>
                        <Row justify="center" style={{marginTop: '3rem'}}>
                            <UserRegistrationForm setSuccess={setSuccess} setError={setError}/>
                        </Row>
                    </Col>
                </Row>
            </main>
        </div>
    );
}

export default UserRegistrationPage