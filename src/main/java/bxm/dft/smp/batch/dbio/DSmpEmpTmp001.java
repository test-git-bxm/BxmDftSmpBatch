/**
 * DBIO 에디터에서 생성된 파일입니다. 인터페이스 파일을 직접 수정하지 마십시오.
 * @Generated Fri Sep 28 11:03:17 KST 2018
 */
package bxm.dft.smp.batch.dbio;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmDataAccess;
import bxm.container.das.ExecutorType;
import bxm.container.das.DasListUpdate;
import bxm.container.das.DasExecutor;

@SuppressWarnings({ "all" })
@BxmDataAccess(mapper = "bxm/dft/smp/batch/dbio/DSmpEmpTmp001.dbio", datasource = "MainDS")
@BxmCategory(logicalName = "SampleEmployeeInfoTMP001")
public interface DSmpEmpTmp001
{

	/**
	 * 샘플용직원정보TMP001등록(insert01)
	 */
	@BxmCategory(logicalName = "샘플용직원정보TMP001등록(insert01)")
	int insert01();

	/**
	 * 샘플용직원정보TMP001초기화(삭제)
	 */
	@BxmCategory(logicalName = "샘플용직원정보TMP001초기화(삭제)")
	int delete01();

	/**
	 * 샘플용직원정보TMP001등록(insert02)
	 * @TestValues 	feduEmpNo=;	feduEmpNm=;	feduOccpNm=;	feduMngrEmpNo=;	feduIpsaDt=;	feduPayAmt=;	feduDeptNo=;
	 */
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory(logicalName = "샘플용직원정보TMP001등록(insert02)")
	int insert02(bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto dSmpEmpTmp001Dto);

	/**
	 * 샘플용직원정보TMP001등록(insert02)
	 * @TestValues 	feduEmpNo=;	feduEmpNm=;	feduOccpNm=;	feduMngrEmpNo=;	feduIpsaDt=;	feduPayAmt=;	feduDeptNo=;
	 */
	@DasListUpdate()
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory(logicalName = "샘플용직원정보TMP001등록(insert02)")
	int insert02(java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto> dSmpEmpTmp001Dto);

	/**
	 * @TestValues 	feduEmpNo=;
	 */
	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001selectList01OutDto> selectList01(
			bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001selectList01InDto dSmpEmpTmp001selectList01InDto);

	@DasExecutor(executor = ExecutorType.CONNECTED_BATCH)
	@BxmCategory()
	java.util.List<bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001selectList01OutDto> selectList02();
}