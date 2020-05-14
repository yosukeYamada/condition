package com.example.common;

import java.time.ZonedDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.domain.error.ErrorResponseBody;
import com.example.domain.error.ExclusiveException;

/**
 * 例外処理を行うハンドラー.
 * 
 * @author yosuke.yamada
 *
 */
@RestControllerAdvice
public class ConditionExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * オリジナルの例外処理するメソッド.
	 * 
	 * @param exception オリジナルのException
	 * @param request
	 * @return オリジナル例外レスポンス
	 */
	@ExceptionHandler(ExclusiveException.class)
	public ResponseEntity<Object> handleMyException(ExclusiveException exception, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		return super.handleExceptionInternal(exception, createErrorResponse(exception, request), headers,
				HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * エラーレスポンスを作るメソッド.
	 * 
	 * @param exception オリジナル例外
	 * @param request   リクエスト
	 * @return オリジナル例外レスポンス
	 */
	private ErrorResponseBody createErrorResponse(ExclusiveException exception, WebRequest request) {
		ErrorResponseBody errorResponseBody = new ErrorResponseBody();
		int responseCode = HttpStatus.BAD_REQUEST.value();
		String responseErrorMessage = HttpStatus.BAD_REQUEST.getReasonPhrase();

		errorResponseBody.setExceptionOccurrenceTime(ZonedDateTime.now());
		errorResponseBody.setStatus(responseCode);
		errorResponseBody.setError(responseErrorMessage);
		errorResponseBody.setMessage(exception.getMessage());
		// TODO Auto-generated method stub
		return errorResponseBody;
	}

	// SpringBoot内で用意されている例外については、ResponseEntityExceptionHandlerクラスで例外ごとに
	// 専用のメソッドが用意されているのでそれをオーバーライドする
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, "MethodArgumentNotValidException", null,
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	// すべての例外をキャッチする
	// どこにもキャッチされなかったらこれが呼ばれる
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		return super.handleExceptionInternal(ex, "handleAllException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
