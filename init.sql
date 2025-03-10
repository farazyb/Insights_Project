-- Create ISO8583Fields_V1987 table
CREATE TABLE IF NOT EXISTS ISO8583Fields_V1987 (
    ISO8583Fields_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message_type INT,
    message_type_identification VARCHAR(255),
    first_bitmap VARCHAR(255),
    second_bitmap VARCHAR(255),
    primary_account_number VARCHAR(255),
    processing_code VARCHAR(255),
    amount_transaction VARCHAR(255),
    amount_settlement VARCHAR(255),
    amount_cardholder_billing VARCHAR(255),
    transmission_date_time VARCHAR(255),
    amount_cardholder_billing_fee VARCHAR(255),
    conversion_rate_settlement VARCHAR(255),
    conversion_rate_cardholder_billing VARCHAR(255),
    system_trace_audit_number VARCHAR(255),
    time_local_transaction VARCHAR(255),
    date_local_transaction VARCHAR(255),
    expiration_date VARCHAR(255),
    date_settlement VARCHAR(255),
    date_conversion VARCHAR(255),
    date_capture VARCHAR(255),
    merchant_type VARCHAR(255),
    acquiring_institution_country_code VARCHAR(255),
    pan_estended_country_code VARCHAR(255),
    forwarding_institution_country_code VARCHAR(255),
    point_of_service_entry_mode VARCHAR(255),
    card_sequence_number VARCHAR(255),
    network_international_identifier VARCHAR(255),
    point_of_service_condition_code VARCHAR(255),
    point_of_service_capture_code VARCHAR(255),
    authorizing_identification_response_length VARCHAR(255),
    amount_transaction_fee VARCHAR(255),
    amount_settlement_fee VARCHAR(255),
    amount_transaction_processing_fee VARCHAR(255),
    amount_settlement_processing_fee VARCHAR(255),
    acquiring_institution_identification_code VARCHAR(255),
    forwarding_institution_identification_code VARCHAR(255),
    primary_account_number_extended VARCHAR(255),
    track2_data VARCHAR(255),
    track3_data VARCHAR(255),
    retrieval_reference_number VARCHAR(255),
    authorization_identification_response VARCHAR(255),
    response_code VARCHAR(255),
    service_restriction_code VARCHAR(255),
    terminal_identification VARCHAR(255),
    card_acceptor_identification_code VARCHAR(255),
    card_acceptor_name_location VARCHAR(255),
    additional_response_data LONGBLOB,
    track1_data VARCHAR(255),
    additional_data_iso VARCHAR(255),
    additional_data_national VARCHAR(255),
    additional_data_private TEXT,
    currency_code_transaction VARCHAR(255),
    currency_code_settlement VARCHAR(255),
    currency_code_cardholder_billing VARCHAR(255),
    pin_data VARCHAR(255),
    security_related_control_information VARCHAR(255),
    additional_amounts VARCHAR(255),
    icc_system_related_data VARCHAR(255),
    icc_system_related_data2 VARCHAR(255),
    reserved_national1 VARCHAR(255),
    reserved_national2 VARCHAR(255),
    reserved_national3 VARCHAR(255),
    reserved_national4 VARCHAR(255),
    reserved_private1 VARCHAR(255),
    reserved_private2 VARCHAR(255),
    reserved_private3 VARCHAR(255),
    message_authentication_code_mac VARCHAR(255),
    bitmap_extended VARCHAR(255),
    settlement_code VARCHAR(255),
    extended_payment_code VARCHAR(255),
    receiving_institution_country_code VARCHAR(255),
    settlement_institution_country_code VARCHAR(255),
    network_management_identification_code VARCHAR(255),
    message_number VARCHAR(255),
    message_number_last VARCHAR(255),
    date_action VARCHAR(255),
    credits_number VARCHAR(255),
    credits_reversal_number VARCHAR(255),
    debits_number VARCHAR(255),
    debits_reversal_number VARCHAR(255),
    transfer_reversal_number VARCHAR(255),
    transfer_reversal_number2 VARCHAR(255),
    inquiries_number VARCHAR(255),
    quthorizations_number VARCHAR(255),
    credits_processing_fee_amount VARCHAR(255),
    credits_transaction_fee_amount VARCHAR(255),
    debits_processing_fee_amount VARCHAR(255),
    debits_transaction_fee_amount VARCHAR(255),
    credits_amount VARCHAR(255),
    credits_reversal_amount VARCHAR(255),
    debits_amount VARCHAR(255),
    debits_reversal_amount VARCHAR(255),
    original_data_elements VARCHAR(255),
    file_update_code VARCHAR(255),
    file_security_code VARCHAR(255),
    response_indicator VARCHAR(255),
    service_indicator VARCHAR(255),
    replacement_amounts VARCHAR(255),
    message_security_code LONGBLOB,
    amount_net_reconcilition VARCHAR(255),
    payee VARCHAR(255),
    settlement_institution_identification_code VARCHAR(255),
    receiving_institution_identification_code VARCHAR(255),
    filename VARCHAR(255),
    account_identification1 VARCHAR(255),
    account_identification2 VARCHAR(255),
    transaction_description VARCHAR(255),
    reserved_for_iso_use1 VARCHAR(255),
    reserved_for_iso_use2 VARCHAR(255),
    reserved_for_iso_use3 VARCHAR(255),
    reserved_for_iso_use4 VARCHAR(255),
    reserved_for_iso_use5 VARCHAR(255),
    reserved_for_iso_use6 VARCHAR(255),
    reserved_for_iso_use7 VARCHAR(255),
    reserved_for_national_use1 VARCHAR(255),
    reserved_for_national_use2 VARCHAR(255),
    reserved_for_national_use3 VARCHAR(255),
    reserved_for_national_use4 VARCHAR(255),
    reserved_for_national_use5 VARCHAR(255),
    reserved_for_national_use6 VARCHAR(255),
    reserved_for_national_use7 VARCHAR(255),
    reserved_for_national_use8 VARCHAR(255),
    reserved_for_private_use1 VARCHAR(255),
    reserved_for_private_use2 LONGBLOB,
    reserved_for_private_use3 VARCHAR(255),
    reserved_for_private_use4 VARCHAR(255),
    statement_data LONGBLOB,
    reserved_for_private_use6 VARCHAR(255),
    reserved_for_private_use7 VARCHAR(255),
    reserved_for_private_use8 VARCHAR(255),
    message_authentication_code VARCHAR(255),
    datetime_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datetime_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create CutOffTime table
CREATE TABLE IF NOT EXISTS CutOffTime (
    cutofftime_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cutoff_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci; 