/**
 * DBIO 에디터에서 생성된 파일입니다. 인터페이스 파일을 직접 수정하지 마십시오.
 * @Generated Fri Sep 28 13:30:52 KST 2018
 */
package bxm.dft.smp.batch.dbio;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmDataAccess;
import bxm.container.das.ExecutorType;
import bxm.container.das.DasListUpdate;
import bxm.container.das.DasExecutor;

@SuppressWarnings({ "all" })
@BxmDataAccess(mapper = "bxm/dft/smp/batch/dbio/DSmpEmpTst001.dbio", datasource = "MainDS")
@BxmCategory(logicalName = "SampleEmployeeInfoTST001")
public interface DSmpEmpTst001
{

	/**
	 * @TestValues 	feduDeptNo=;
	 */
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto> selectList01(
			bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01InDto dSmpEmpTst001selectList01InDto);

	/**
	 * @TestValues 	feduEmpNo=;	feduEmpNm=;	feduOccpNm=;	feduMngrEmpNo=;	feduIpsaDt=;	feduPayAmt=;	feduDeptNo=;
	 */
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	int update01(bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto dSmpEmpTst001selectList01OutDto);

	/**
	 * @TestValues 	feduEmpNo=;	feduEmpNm=;	feduOccpNm=;	feduMngrEmpNo=;	feduIpsaDt=;	feduPayAmt=;	feduDeptNo=;
	 */
	@DasListUpdate()
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	int update01(
			java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto> dSmpEmpTst001selectList01OutDto);

	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto> selectList02();
}