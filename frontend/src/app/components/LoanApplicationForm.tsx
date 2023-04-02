import React, {useState} from 'react';
import {Button, Col, Form, Input, Row} from 'antd';
import {LoanRequest} from '../types/DataTypes';
import {validateAmount} from "../utils/utils";

interface Props {
    handleSubmit: (loanRequest: LoanRequest) => void
}

const LoanApplicationForm: React.FC<Props> = ({handleSubmit}) => {

    const [loanRequest, setLoanRequest] = useState<LoanRequest>({
        loanAmount: '0',
        equityAmount: '0',
        salaryAmount: '0',
    });

    const isDisabled = (): boolean => {
        return !(loanRequest.loanAmount ||
            loanRequest.equityAmount ||
            loanRequest.salaryAmount
        );
    };

    return (
        <Form
            layout="vertical"
            onFinish={() => handleSubmit(loanRequest)}
        >
            <Row gutter={[16, 16]}>
                <Col span={24}>
                    <Form.Item
                        label="Loan Amount"
                        name="loanAmount"
                        rules={[
                            {required: true, message: 'Please enter the loan amount!'},
                            {validator: validateAmount},
                        ]}
                    >
                        <Input
                            placeholder="Enter loan amount"
                            onChange={(e) =>
                                setLoanRequest({
                                    ...loanRequest,
                                    loanAmount: e.target.value,
                                })
                            }
                            value={loanRequest.loanAmount}
                        />
                    </Form.Item>
                </Col>
                <Col span={24}>
                    <Form.Item
                        label="Equity Amount"
                        name="equityAmount"
                        rules={[
                            {required: true, message: 'Please enter the equity amount!'},
                            {validator: validateAmount},
                        ]}
                    >
                        <Input
                            placeholder="Enter equity amount"
                            onChange={(e) =>
                                setLoanRequest({
                                    ...loanRequest,
                                    equityAmount: e.target.value,
                                })
                            }
                            value={loanRequest.equityAmount}
                        />
                    </Form.Item>
                </Col>
                <Col span={24}>
                    <Form.Item
                        label="Salary Amount"
                        name="salaryAmount"
                        rules={[
                            {required: true, message: 'Please enter the salary amount!'},
                            {validator: validateAmount},
                        ]}
                    >
                        <Input
                            placeholder="Enter salary amount"
                            onChange={(e) =>
                                setLoanRequest({
                                    ...loanRequest,
                                    salaryAmount: e.target.value,
                                })
                            }
                            value={loanRequest.salaryAmount}
                        />
                    </Form.Item>
                </Col>
                <Col span={24}>
                    <Button
                        type="primary"
                        htmlType="submit"
                        disabled={isDisabled()}
                    >
                        Submit loan application
                    </Button>
                </Col>
            </Row>
        </Form>
    );
};

export default LoanApplicationForm;
