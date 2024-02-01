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

import bxm.batch.dft.context.DefaultBatchApplicationContext;
import bxm.common.annotaion.BxmCategory;
import bxm.common.util.StringUtils;
import bxm.container.annotation.BxmBean;
import bxm.context.das.DasUtils;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpDBToFixedBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpDelimitedToDBBtch02Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01InDto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto;

@BxmBean("MSmpDocMng")
@Scope("step")
@BxmCategory(logicalName = "샘플 배치모듈", description = "샘플 배치모듈")
public class MSmpDocMng implements ItemStream, ItemReader<MSmpDBToFixedBtch01Dto>
				, ItemProcessor<MSmpDBToFixedBtch01Dto, MSmpDelimitedToDBBtch02Dto> 
				, ItemWriter<MSmpDelimitedToDBBtch02Dto>{
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DSmpEmpTst001 dSmpEmpTst001; // 샘플용직원정보TST
	private DSmpEmpTmp001 dSmpEmpTmp100;
	private Iterator<DSmpEmpTst001selectList01OutDto> iterator;
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException 
	{
		dSmpEmpTst001 = DefaultApplicationContext.getBean(dSmpEmpTst001, DSmpEmpTst001.class);
		
		String feduDeptNo = DefaultBatchApplicationContext.getJobParameter("deptNo");
		if(StringUtils.isEmpty(feduDeptNo))
		{
			throw new ItemStreamException("배치 입력아규먼트 'deptNo' 값이 존재하지 않습니다.");
		}
		
		DSmpEmpTst001selectList01InDto inDto = new DSmpEmpTst001selectList01InDto();
		inDto.setFeduDeptNo(Integer.parseInt(feduDeptNo));
		iterator = dSmpEmpTst001.selectList01(inDto).iterator();
		if(iterator == null)
		{
			throw new ItemStreamException("connected result is not prepared.");
		}
	}
	
	@Override
	public MSmpDBToFixedBtch01Dto read() throws Exception, UnexpectedInputException, 
			ParseException,	NonTransientResourceException 
	{
		MSmpDBToFixedBtch01Dto out = null;
		if(iterator.hasNext())
		{
			out = new MSmpDBToFixedBtch01Dto();
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
	
	@Override
	public MSmpDelimitedToDBBtch02Dto process(MSmpDBToFixedBtch01Dto in)
			throws Exception 
	{
		MSmpDelimitedToDBBtch02Dto out = new MSmpDelimitedToDBBtch02Dto();
		
		out.setFeduEmpNo(in.getFeduEmpNo());				// set [FW샘플 임직원번호]
		out.setFeduEmpNm(in.getFeduEmpNm());				// set [FW샘플 임직원명]
		out.setFeduOccpNm(in.getFeduOccpNm());			// set [FW샘플 직업명]
		out.setFeduMngrEmpNo(in.getFeduMngrEmpNo());		// set [FW샘플 관리자임직원번호]
		out.setFeduHireDt(in.getFeduHireDt());			// set [FW샘플 입사일자]
		out.setFeduPayAmt(in.getFeduPayAmt());			// set [FW샘플 급여금액]
		out.setFeduDeptNo(in.getFeduDeptNo());			// set [FW샘플 부서번호]
		
		return out;
	}
	
	@Override
	public void write(List<? extends MSmpDelimitedToDBBtch02Dto> in)
			throws Exception 
	{
		dSmpEmpTmp100 = DefaultApplicationContext.getBean(dSmpEmpTmp100, DSmpEmpTmp001.class);
		
		List<DSmpEmpTmp001Dto> dSmpEmpTmp100insert02InDtos = new ArrayList<DSmpEmpTmp001Dto>();
		for(MSmpDelimitedToDBBtch02Dto mSmpDelimitedToDBBtch02Dto : in)
		{
			DSmpEmpTmp001Dto smpEmpTmp001Dto = new DSmpEmpTmp001Dto();
			
			smpEmpTmp001Dto.setFeduEmpNo(mSmpDelimitedToDBBtch02Dto.getFeduEmpNo());			// set [FW샘플 임직원번호]
			smpEmpTmp001Dto.setFeduEmpNm(mSmpDelimitedToDBBtch02Dto.getFeduEmpNm());			// set [FW샘플 임직원명]
			smpEmpTmp001Dto.setFeduOccpNm(mSmpDelimitedToDBBtch02Dto.getFeduOccpNm());			// set [FW샘플 직업명]
			smpEmpTmp001Dto.setFeduMngrEmpNo(mSmpDelimitedToDBBtch02Dto.getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
			smpEmpTmp001Dto.setFeduHireDt(mSmpDelimitedToDBBtch02Dto.getFeduHireDt());			// set [FW샘플 입사일자]
			smpEmpTmp001Dto.setFeduPayAmt(mSmpDelimitedToDBBtch02Dto.getFeduPayAmt());			// set [FW샘플 급여금액]
			smpEmpTmp001Dto.setFeduDeptNo(mSmpDelimitedToDBBtch02Dto.getFeduDeptNo());			// set [FW샘플 부서번호]
		}
		
		dSmpEmpTmp100.insert02(dSmpEmpTmp100insert02InDtos);
	}
	
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException 
	{
	}
	
	@Override
	public void close() throws ItemStreamException 
	{
		DasUtils.disconnectDasExecutor(iterator);
	}
}
