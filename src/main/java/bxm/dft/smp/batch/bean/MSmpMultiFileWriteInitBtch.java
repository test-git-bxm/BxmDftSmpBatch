package bxm.dft.smp.batch.bean;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;

import bxm.batch.dft.context.DefaultBatchApplicationContext;
import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;

/**
 * @file         bxm.jbk.smp.bean.MSmpMultiFileWriteInitBtch.java
 * @filetype     java source file
 * @brief
 * @author       개발자(한글이름)
 * @version      0.1
 * @history
 * <pre>
 * 버전          		성명                   				일자             			 변경내용
 * -------      ----------------       	-----------		-----------------
 * 0.1			개발자(한글이름)      			2015. 01. 06.	신규 작성
 * </pre>
 */

@BxmBean("MSmpMultiFileWriteInitBtch")
@Scope("step")
@BxmCategory(logicalName = "MULTI FILE WRITE 샘플 초기화", description = "MULTI FILE WRITE 샘플 초기화")
public class MSmpMultiFileWriteInitBtch implements Tasklet{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Tasklet
	 * - 단순 DB CRUD 수행이나 반드시 한번에 Commit/Rollback 되어야 하는 배치에서 사용하는 패턴.
	 * - 참고 1 : Tasklet에서는 BeforeStep, AfterStep을 사용할 수 없다.
	 * - 참고 2 : Tasklet에서 배치작업점검로그를 남기려고 한다면 배치 종료전에 API를 호출하여 남길 수 있도록 한다.(AfterStep 호출되지 않음)
	 */
	@Override
	@BxmCategory(logicalName = "Tasklet 샘플(execute)", description = "Tasklet 샘플(execute)")
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception 
	{
		
		/**
		 * 배치입력아규먼트에서 입력된 Order Date 를 가지고 온다.
		 */
		String oDate = DefaultBatchApplicationContext.getJobParameter("oDate");
		
		/**
		 * 초기화 처리
		 * - 파일이 있으면 삭제처리한다.
		 * - 참고 : 기본적으로 FW에서 제공하는 WFixed, WDelimited 등을 사용하면 Default로 OverWrite 된다. 
		 */
		File writeFixedFile = new File("/data1/prod/bxm400/dat/fixed_file_"+ oDate + ".txt");
		if(writeFixedFile.exists()) 
		{
			boolean isResult = writeFixedFile.delete();
			if(!isResult)
			{
				logger.error("{} 파일을 Delete에 실패하였습니다.", writeFixedFile);
			}
		}
		
		File writeDelimitedFile = new File("/data1/prod/bxm400/dat/delimited_file_"+ oDate + ".txt");
		if(writeDelimitedFile.exists())
		{
			boolean isResult = writeDelimitedFile.delete();
			if(!isResult)
			{
				logger.error("{} 파일을 Delete에 실패하였습니다.", writeDelimitedFile);
			}
		}
		
		/**
		 * Tasklet을 종료처리
		 * - Tasklet을 완료처리/ 혹은 반복 처리등을 Return 값에 따라 처리 할 수 있다.
		 *  - RepeatStatus.FINISHED : Tasklet 완료(정상종료)
		 *  - RepeatStatus.CONTINUABLE : Tasklet의 excute메소드 다시 수행.
		 *  - 만일 에러를 발생하고 싶다면 ApplicationException을 throw 하면 된다.
		 */
		return RepeatStatus.FINISHED;
	}
}