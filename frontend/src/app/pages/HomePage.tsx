import React from "react";
import UserRegisterButton from "../components/UserRegisterButton";
import { Row, Col } from 'antd';
import LoginButton from "../components/LoginButton";

const HomePage: React.FC = () => {

    return (
        <div>
            <main>
                <Row justify="center" style={{ marginTop: '5rem' }}>
                    <h1>Welcome to DNB's Real Estate loan portal</h1>
                </Row>
                <Row justify="center" style={{ marginTop: '5rem' }}>
                    <Col span={10}>
                        <Row justify="center" style={{ marginTop: '3rem' }}>
                            <LoginButton/>
                        </Row>
                        <Row justify="center" style={{ marginTop: '3rem' }}>
                            <UserRegisterButton/>
                        </Row>
                    </Col>
                </Row>
            </main>
        </div>
    );
}

export default HomePage