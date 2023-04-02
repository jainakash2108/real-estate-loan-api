import React, {useState} from 'react';
import {UserRequest} from '../types/DataTypes';
import {Button, Form, Input} from 'antd';
import {registerUser} from '../apis/api';
import {validateCustomerSSN, validateEmail, validatePassword} from "../utils/utils";

interface Props {
    setSuccess: React.Dispatch<React.SetStateAction<string>>
    setError: React.Dispatch<React.SetStateAction<string>>
}

const UserRegistrationForm: React.FC<Props> = ({setSuccess, setError}) => {
    const [userRequest, setUserRequest] = useState<UserRequest>({
        name: '',
        email: '',
        customerSSN: '',
        password: '',
    });

    const isDisabled = (): boolean => {
        return !(userRequest.name &&
            userRequest.email &&
            userRequest.customerSSN &&
            userRequest.password);
    };

    const onFinish = (user: UserRequest) => {
        registerUser(userRequest)
            .then(response => {
                setUserRequest({
                    name: '',
                    email: '',
                    customerSSN: '',
                    password: '',
                })
                setSuccess(`User ${user.name} has been created successfully`)
                setError('')
            })
            .catch(error => {
                console.log('Error:', error);
                setSuccess('')
                const errorMessage = error instanceof Error ? error.message : String(error);
                setError(errorMessage)
            })
    };


    return (
        <Form
            onFinish={onFinish}
            layout="vertical"
        >
            <Form.Item
                label="Name"
                name="name"
                rules={[{required: true, message: 'Please enter your name'}]}
            >
                <Input
                    id="name"
                    type="text"
                    placeholder="Enter name"
                    onChange={(e) =>
                        setUserRequest({...userRequest, name: e.target.value})
                    }
                    value={userRequest.name}
                />
            </Form.Item>
            <Form.Item
                label="Email"
                name="email"
                rules={[
                    {required: true, message: 'Please enter your email'},
                    {validator: (_, value) => validateEmail(value) ? Promise.resolve() : Promise.reject('Invalid email address')}
                ]}
            >
                <Input
                    id="email"
                    type="email"
                    placeholder="Enter email"
                    onChange={(e) =>
                        setUserRequest({...userRequest, email: e.target.value})
                    }
                    value={userRequest.email}
                />
            </Form.Item>
            <Form.Item
                label="Customer SSN"
                name="customerSSN"
                rules={[
                    {required: true, message: 'Please enter your customer SSN'},
                    {validator: validateCustomerSSN},
                ]}
            >
                <Input
                    id="customerSSN"
                    type="text"
                    maxLength={11}
                    placeholder="Enter customer SSN"
                    onChange={(e) => {
                        const value = e.target.value.replace(/\D/g, '');
                        setUserRequest({...userRequest, customerSSN: value});
                    }}
                    value={userRequest.customerSSN}
                />
            </Form.Item>
            <Form.Item
                label="Password"
                name="password"
                rules={[
                    {required: true, message: 'Please enter your password'},
                    {
                        validator: validatePassword,
                    },
                ]}
            >
                <Input.Password
                    id="password"
                    type="password"
                    placeholder="Enter password"
                    onChange={(e) =>
                        setUserRequest({...userRequest, password: e.target.value})
                    }
                    value={userRequest.password}
                />
            </Form.Item>
            <Form.Item>
                <Button
                    type="primary"
                    htmlType="submit"
                    disabled={isDisabled()}
                    id="submit"
                >
                    Register
                </Button>
            </Form.Item>
        </Form>
    );
};

export default UserRegistrationForm;
