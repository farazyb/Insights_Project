package com.sea.reporter.model.repository;

import com.sea.reporter.model.dto.IsoDTO;
import com.sea.reporter.model.entity.ISO8583FieldsV1987;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ISO8583FieldsV1987Repository extends JpaRepository<ISO8583FieldsV1987, Long> {

    final String getByDatetimeCreatedQuery = "WITH RECURSIVE cteReports AS ( \n" +
            "    SELECT \n" +
            "    processing_code, \n" +
            "    primary_account_number,  \n" +
            "    message_type_identification, \n" +
            "    system_trace_audit_number, \n" +
            "    amount_transaction, \n" +
            "    retrieval_reference_number, \n" +
            "    terminal_identification, \n" +
            "    datetime_created, \n" +
            "    additional_data_private, \n" +
            "    reserved_private2, \n" +
            "    time_local_transaction, \n" +
            "    date_local_transaction, \n" +
            "    card_acceptor_identification_code, \n" +
            "    date_capture, \n" +
            "    debits_number, \n" +
            "    response_code \n" +
            "    FROM ISO8583Fields_V1987 \n" +
            "    WHERE datetime_created >= :from AND datetime_created < :to \n" +
            ") \n" +
            "SELECT \n" +
            "    processing_code AS processingCode, \n" +
            "    primary_account_number AS primaryAccountNumber, \n" +
            "    message_type_identification AS messageTypeIdentification, \n" +
            "    system_trace_audit_number AS systemTraceAuditNumber, \n" +
            "    amount_transaction AS amountTransaction, \n" +
            "    retrieval_reference_number AS retrievalReferenceNumber, \n" +
            "    terminal_identification AS terminalIdentification, \n" +
            "    datetime_created AS datetimeCreated, \n" +
            "    additional_data_private AS additionalDataPrivate, \n" +
            "    reserved_private2 AS reservedPrivate2, \n" +
            "    time_local_transaction AS timeLocalTransaction, \n" +
            "    date_local_transaction AS dateLocalTransaction, \n" +
            "    card_acceptor_identification_code AS CardAcceptorIdentificationCode, \n" +
            "    date_capture AS dateCapture \n" +
            "FROM ( \n" +
            "    SELECT t7.* \n" +
            "    FROM ( \n" +
            "        SELECT t5.* \n" +
            "        FROM cteReports AS t5 \n" +
            "        INNER JOIN cteReports AS t6 ON t5.retrieval_reference_number = t6.retrieval_reference_number \n" +
            "        WHERE t5.debits_number = '5' AND t5.processing_code <> '' \n" +
            "        AND ( \n" +
            "            (LEFT(t5.message_type_identification, 2) = LEFT(t6.message_type_identification, 2)) \n" +
            "            OR (t5.processing_code = '310000') \n" +
            "            OR (t5.processing_code = '340000') \n" +
            "        ) \n" +
            "        AND t6.response_code = '00' \n" +
            "    ) AS t7 \n" +
            "    WHERE NOT EXISTS ( \n" +
            "        SELECT 1 \n" +
            "        FROM cteReports AS t4 \n" +
            "        INNER JOIN ( \n" +
            "            SELECT t.* \n" +
            "            FROM cteReports AS t \n" +
            "            INNER JOIN cteReports AS t2 ON t.retrieval_reference_number = t2.retrieval_reference_number \n" +
            "            WHERE t.debits_number = '5' \n" +
            "            AND t.processing_code <> '' \n" +
            "            AND LEFT(t.message_type_identification, 2) = LEFT(t2.message_type_identification, 2) \n" +
            "            AND t2.response_code = '00' \n" +
            "        ) AS t3 ON t4.message_type_identification = '0430' \n" +
            "        AND t4.response_code = '00' \n" +
            "        AND t4.retrieval_reference_number = t3.retrieval_reference_number \n" +
            "        AND t3.terminal_identification = t4.terminal_identification \n" +
            "        WHERE t7.retrieval_reference_number = t4.retrieval_reference_number \n" +
            "    ) \n" +
            ") AS t12 \n" +
            "WHERE t12.additional_data_private LIKE '%UD%' \n" +
            "AND t12.message_type_identification = '0200' \n" +
            "ORDER BY t12.datetime_created";

    final String getByDatetimeCreatedAAndDateCapture = "WITH RECURSIVE cteReports AS ( \n" +
            "    SELECT \n" +
            "    processing_code, \n" +
            "    primary_account_number,  \n" +
            "    message_type_identification, \n" +
            "    system_trace_audit_number, \n" +
            "    amount_transaction, \n" +
            "    retrieval_reference_number, \n" +
            "    terminal_identification, \n" +
            "    datetime_created, \n" +
            "    additional_data_private, \n" +
            "    reserved_private2, \n" +
            "    time_local_transaction, \n" +
            "    date_local_transaction, \n" +
            "    card_acceptor_identification_code, \n" +
            "    date_capture, \n" +
            "    debits_number, \n" +
            "    response_code \n" +
            "    FROM ISO8583Fields_V1987 \n" +
            "    WHERE datetime_created >= :from AND datetime_created < :to \n" +
            ") \n" +
            "SELECT \n" +
            "    processing_code AS processingCode, \n" +
            "    primary_account_number AS primaryAccountNumber, \n" +
            "    message_type_identification AS messageTypeIdentification, \n" +
            "    system_trace_audit_number AS systemTraceAuditNumber, \n" +
            "    amount_transaction AS amountTransaction, \n" +
            "    retrieval_reference_number AS retrievalReferenceNumber, \n" +
            "    terminal_identification AS terminalIdentification, \n" +
            "    datetime_created AS datetimeCreated, \n" +
            "    additional_data_private AS additionalDataPrivate, \n" +
            "    reserved_private2 AS reservedPrivate2, \n" +
            "    time_local_transaction AS timeLocalTransaction, \n" +
            "    date_local_transaction AS dateLocalTransaction, \n" +
            "    card_acceptor_identification_code AS CardAcceptorIdentificationCode, \n" +
            "    date_capture AS dateCapture \n" +
            "FROM ( \n" +
            "    SELECT t7.* \n" +
            "    FROM ( \n" +
            "        SELECT t5.* \n" +
            "        FROM cteReports AS t5 \n" +
            "        INNER JOIN cteReports AS t6 ON t5.retrieval_reference_number = t6.retrieval_reference_number \n" +
            "        WHERE t5.debits_number = '5' AND t5.processing_code <> '' \n" +
            "        AND ( \n" +
            "            (LEFT(t5.message_type_identification, 2) = LEFT(t6.message_type_identification, 2)) \n" +
            "            OR (t5.processing_code = '310000') \n" +
            "            OR (t5.processing_code = '340000') \n" +
            "        ) \n" +
            "        AND t6.response_code = '00' \n" +
            "    ) AS t7 \n" +
            "    WHERE NOT EXISTS ( \n" +
            "        SELECT 1 \n" +
            "        FROM cteReports AS t4 \n" +
            "        INNER JOIN ( \n" +
            "            SELECT t.* \n" +
            "            FROM cteReports AS t \n" +
            "            INNER JOIN cteReports AS t2 ON t.retrieval_reference_number = t2.retrieval_reference_number \n" +
            "            WHERE t.debits_number = '5' \n" +
            "            AND t.processing_code <> '' \n" +
            "            AND LEFT(t.message_type_identification, 2) = LEFT(t2.message_type_identification, 2) \n" +
            "            AND t2.response_code = '00' \n" +
            "        ) AS t3 ON t4.message_type_identification = '0430' \n" +
            "        AND t4.response_code = '00' \n" +
            "        AND t4.retrieval_reference_number = t3.retrieval_reference_number \n" +
            "        AND t3.terminal_identification = t4.terminal_identification \n" +
            "        WHERE t7.retrieval_reference_number = t4.retrieval_reference_number \n" +
            "    ) \n" +
            ") AS t12 \n" +
            "WHERE date_capture >= :fromDateCapture \n" +
            "AND date_capture < :toDateCapture \n" +
            "AND t12.additional_data_private LIKE '%UD%' \n" +
            "AND t12.message_type_identification = '0200' \n" +
            "ORDER BY t12.datetime_created";

    @Transactional
    @Query(nativeQuery = true, value = getByDatetimeCreatedQuery)
    public List<IsoDTO> getByDatetimeCreated(@Param("from") String from, @Param("to") String to);

    @Transactional
    @Query(nativeQuery = true, value = getByDatetimeCreatedAAndDateCapture)
    public List<IsoDTO> getByDatetimeCreatedAAndDateCapture(@Param("from") String from, @Param("to") String to,
                                                            @Param("fromDateCapture") String fromDateCapture,
                                                            @Param("toDateCapture") String toDateCapture);
}
