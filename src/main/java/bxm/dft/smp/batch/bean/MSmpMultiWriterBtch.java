package bxm.dft.smp.batch.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.context.das.DasUtils;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpMultiWriterBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiWriterBtch02Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiWriterBtch03Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiWriterBtch04Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto;

/**
 * @file         	bxm.jbk.smp.bean.MSmpDelimitedMultiTypeBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          			성명                   				일자              				변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)       		2015. 01. 06.       신규 작성
 * </pre>
 */

@BxmBean("MSmpMultiWriterBtch")
@Scope("step")
@BxmCategory(logicalName = "Multi Writer 샘플", description = "Multi Writer 샘플")
public class MSmpMultiWriterBtch implements ItemReader<MSmpMultiWriterBtch01Dto>, ItemProcessor<MSmpMultiWriterBtch01Dto, MSmpMultiWriterBtch02Dto>
				, ItemWriter<MSmpMultiWriterBtch02Dto>, ItemStream {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DSmpEmpTst001 dSmpEmpTst001; 
	private DSmpEmpTmp001 dSmpEmpTmp100;
	
	private Iterator<DSmpEmpTst001selectList01OutDto> iterator;
	
	/**
	 * open
	 * - 초기화를 위해 구현해야 하는 Method 로서 스텝이 시작되기 전에 FW에서 최초 1번 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 open", description = "DB TO DB 샘플 open")
	public void open(ExecutionContext executionContext) throws ItemStreamException 
	{
		dSmpEmpTst001 = DefaultApplicationContext.getBean(dSmpEmpTst001, DSmpEmpTst001.class);
		dSmpEmpTmp100 = DefaultApplicationContext.getBean(dSmpEmpTmp100, DSmpEmpTmp001.class);
		
		/**
		 * - 샘플용 직원정보를 Iterator로 가져온다.
		 */
		
		iterator = dSmpEmpTst001.selectList02().iterator();
		if(iterator == null)
		{
			throw new ItemStreamException("connected result is not prepared.");
		}
		
		/**
		 * - 초기화 처리
		 */
		dSmpEmpTmp100.delete01();
	}
	
	/**
	 * read
	 * - 처리 대상 데이터를 건 별로 DB 또는 파일에서 읽어오는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 read", description = "DB TO DB 샘플 read")
	public MSmpMultiWriterBtch01Dto read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException 
	{
		MSmpMultiWriterBtch01Dto out = null;
		if(iterator.hasNext())
		{
			out = new MSmpMultiWriterBtch01Dto();
			DSmpEmpTst001selectList01OutDto input = iterator.next();
			
			out.setFeduEmpNo(input.getFeduEmpNo());			// set [FW샘플 임직원번호]
			out.setFeduEmpNm(input.getFeduEmpNm());			// set [FW샘플 임직원명]
			out.setFeduOccpNm(input.getFeduOccpNm());		// set [FW샘플 직업명]
			out.setFeduMngrEmpNo(input.getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
			out.setFeduHireDt(input.getFeduHireDt());		// set [FW샘플 입사일자]
			out.setFeduPayAmt(input.getFeduPayAmt());		// set [FW샘플 급여금액]
			out.setFeduDeptNo(input.getFeduDeptNo());		// set [FW샘플 부서번호]
		}
		
		return out;
	}
	
	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 Multi Type 처리", description = "process : 샘플용 직원정보 Multi Type 처리")
	public MSmpMultiWriterBtch02Dto process(MSmpMultiWriterBtch01Dto in) throws Exception 
	{
		MSmpMultiWriterBtch02Dto out = new MSmpMultiWriterBtch02Dto();
		
		/**
		 * 개별 업무로직 수행
		 * ...
		 * ...
		 */
		
		/**
		 * 부서 번호에 따라 io 설정 처리
		 * - 부서번호 20이하면 sub01, 그 외에는 sub02 로 설정
		 */
		if(in.getFeduDeptNo() <= 20)
		{
			MSmpMultiWriterBtch03Dto sub01 = new MSmpMultiWriterBtch03Dto();
			
			sub01.setFeduEmpNo(in.getFeduEmpNo());			// set [FW샘플 임직원번호]
			sub01.setFeduEmpNm(in.getFeduEmpNm());			// set [FW샘플 임직원명]
			sub01.setFeduOccpNm(in.getFeduOccpNm());		// set [FW샘플 직업명]
			sub01.setFeduMngrEmpNo(in.getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
			sub01.setFeduHireDt(in.getFeduHireDt());		// set [FW샘플 입사일자]
			sub01.setFeduPayAmt(in.getFeduPayAmt());		// set [FW샘플 급여금액]
			sub01.setFeduDeptNo(in.getFeduDeptNo());		// set [FW샘플 부서번호]
			
			out.setSub01(sub01);
		}
		else
		{
			MSmpMultiWriterBtch04Dto sub02 = new MSmpMultiWriterBtch04Dto();
			
			sub02.setFeduEmpNo(in.getFeduEmpNo());			// set [FW샘플 임직원번호]
			sub02.setFeduEmpNm(in.getFeduEmpNm());			// set [FW샘플 임직원명]
			sub02.setFeduOccpNm(in.getFeduOccpNm());		// set [FW샘플 직업명]
			sub02.setFeduMngrEmpNo(in.getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
			sub02.setFeduHireDt(in.getFeduHireDt());		// set [FW샘플 입사일자]
			sub02.setFeduPayAmt(in.getFeduPayAmt());		// set [FW샘플 급여금액]
			sub02.setFeduDeptNo(in.getFeduDeptNo());		// set [FW샘플 부서번호]
			
			out.setSub02(sub02);
		}
		
		return out;
	}
	
	/**
	 * write
	 * - 처리 대상 결과 데이터를 커밋 단위 별로 DB 또는 파일에 기록처리하는 역할을 수행한다.
	 */
	@Override
	public void write(List<? extends MSmpMultiWriterBtch02Dto> in) throws Exception 
	{
		dSmpEmpTmp100 = DefaultApplicationContext.getBean(dSmpEmpTmp100, DSmpEmpTmp001.class);
		
		/**
		 * List로 전달된 Item를 Loop를 돌면서 DB에 Insert할 DTO를 List형태로 설정한다.
		 */
		List<DSmpEmpTmp001Dto> dSmpEmpTmp100insert02InDtos = new ArrayList<DSmpEmpTmp001Dto>();
		for(MSmpMultiWriterBtch02Dto mSmpDBToDBBtch02Dto : in)
		{
			DSmpEmpTmp001Dto smpEmpTmp001Dto = new DSmpEmpTmp001Dto();
			
			if(mSmpDBToDBBtch02Dto.getSub01() != null)
			{
				smpEmpTmp001Dto.setFeduEmpNo(mSmpDBToDBBtch02Dto.getSub01().getFeduEmpNo());			// set [FW샘플 임직원번호]
				smpEmpTmp001Dto.setFeduEmpNm(mSmpDBToDBBtch02Dto.getSub01().getFeduEmpNm());			// set [FW샘플 임직원명]
				smpEmpTmp001Dto.setFeduOccpNm(mSmpDBToDBBtch02Dto.getSub01().getFeduOccpNm());			// set [FW샘플 직업명]
				smpEmpTmp001Dto.setFeduMngrEmpNo(mSmpDBToDBBtch02Dto.getSub01().getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
				smpEmpTmp001Dto.setFeduHireDt(mSmpDBToDBBtch02Dto.getSub01().getFeduHireDt());			// set [FW샘플 입사일자]
				smpEmpTmp001Dto.setFeduPayAmt(mSmpDBToDBBtch02Dto.getSub01().getFeduPayAmt());			// set [FW샘플 급여금액]
				smpEmpTmp001Dto.setFeduDeptNo(mSmpDBToDBBtch02Dto.getSub01().getFeduDeptNo());			// set [FW샘플 부서번호]
				
				dSmpEmpTmp100insert02InDtos.add(smpEmpTmp001Dto);
			}
			if(mSmpDBToDBBtch02Dto.getSub02() != null)
			{
				smpEmpTmp001Dto.setFeduEmpNo(mSmpDBToDBBtch02Dto.getSub02().getFeduEmpNo());			// set [FW샘플 임직원번호]
				smpEmpTmp001Dto.setFeduEmpNm(mSmpDBToDBBtch02Dto.getSub02().getFeduEmpNm());			// set [FW샘플 임직원명]
				smpEmpTmp001Dto.setFeduOccpNm(mSmpDBToDBBtch02Dto.getSub02().getFeduOccpNm());			// set [FW샘플 직업명]
				smpEmpTmp001Dto.setFeduMngrEmpNo(mSmpDBToDBBtch02Dto.getSub02().getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
				smpEmpTmp001Dto.setFeduHireDt(mSmpDBToDBBtch02Dto.getSub02().getFeduHireDt());			// set [FW샘플 입사일자]
				smpEmpTmp001Dto.setFeduPayAmt(mSmpDBToDBBtch02Dto.getSub02().getFeduPayAmt());			// set [FW샘플 급여금액]
				smpEmpTmp001Dto.setFeduDeptNo(mSmpDBToDBBtch02Dto.getSub02().getFeduDeptNo());			// set [FW샘플 부서번호]
				
				dSmpEmpTmp100insert02InDtos.add(smpEmpTmp001Dto);
			}
		}
		
		/**
		 * insert 처리
		 * - 참고 1 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 bulk로 쿼리가 수행이 된다.
		 * - 참고 2 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 수행이 되기때문에 return 값은 임의의 음수 값이다.(의미없음)
		 */
		dSmpEmpTmp100.insert02(dSmpEmpTmp100insert02InDtos);
	}

	/**
	 * update
	 * - 진행 상태를 기록하기 위해 구현해야 하는 Method로서 구간 별로 Commit 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 update", description = "DB TO DB 샘플 update")
	public void update(ExecutionContext executionContext) throws ItemStreamException 
	{
		
	}
	
	/**
	 * close
	 * - 리소스 정리 작업을 위해 구현해야 하는 Method로서 Step 완료 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 close", description = "DB TO DB 샘플 close")
	public void close() throws ItemStreamException 
	{
		DasUtils.disconnectDasExecutor(iterator);
	}
}