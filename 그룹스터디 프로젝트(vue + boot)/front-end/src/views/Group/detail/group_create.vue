<template>
    <div class="create">
        <div class="createContainer">
            <div class="title">그룹 스터디 생성</div>
            <el-form :model="groupDto" label-position="top" :rules="rules" ref="groupDto" label-width="120px" class="demo-groupDto" v-loading="loading">
                <!-- 그룹명 설정 -->
                <el-form-item label="그룹 스터디명" prop="groupName">
                    <el-input placeholder="그룹 스터디명을 적어주세요" v-model="groupDto.groupName" style="width: 85%"></el-input>
                </el-form-item>
                <!-- 주제선택 -->
                <el-form-item label="스터디 주제" prop="inter">
                    <el-select v-model="groupDto.inter" placeholder="주제를 선택해주세요" style="width: 85%">
                        <el-option-group v-for="bigInter in InterListAll" :key="bigInter.interBigSeq" :value="bigInter.interBigSeq" :label="bigInter.bigName">
                            <el-option v-for="item in bigInter.interSmallDtos" :key="item.interSmallSeq" :value="item.interSmallSeq" :label="item.smallName"/>
                        </el-option-group>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="작은 주제" prop="region2">
                    <el-select v-model="ruleForm.region2" placeholder="선택해주세요">
                        <el-option v-for="smallInter in smallInterList" :key="smallInter.interSmallSeq" :value="smallInter.interSmallSeq" :label="smallInter.smallName"></el-option>
                    </el-select>
                </el-form-item> -->
                <!-- 인원설정 -->
                <el-form-item label="최대인원" prop="maxMember">
                    <el-input-number size="" v-model="groupDto.maxMember" :min="4" :max="10"></el-input-number>
                </el-form-item>
                <!-- 기간설정 -->
                <el-form-item label="기간설정" prop="date">
                    <el-date-picker
                        style="width: 85%"
                        v-model="groupDto.date"
                        type="daterange"
                        align="right"
                        unlink-panels
                        range-separator=" ~ "
                        start-placeholder="시작일"
                        end-placeholder="종료일"
                        :picker-options="pickerOptions">
                    </el-date-picker>
                </el-form-item>
                <!-- 장소선택 -->
                <el-form-item label="지역선택" prop="address">
                    <el-button type="primary" round @click="execDaumPostcode">우편번호찾기</el-button>
                    <el-input placeholder="주소" v-model="groupDto.address" style="width: 85%" readonly="readonly"></el-input>
                    <el-input placeholder="상세주소" v-model="groupDto.extraAddress" style="width: 85%" readonly="readonly"></el-input>
                </el-form-item>
                <!-- 주간일정 -->
                <el-form-item label="주간 일정" prop="groupSchedule">
                    <el-checkbox-group v-model="groupDto.groupSchedule">
                        <el-checkbox label="monday" id="sch_check0"><label for="sch_check0">월</label></el-checkbox>
                        <el-checkbox label="tuesday" id="sch_check1"><label for="sch_check1">화</label></el-checkbox>
                        <el-checkbox label="wednesday" id="sch_check2"><label for="sch_check2">수</label></el-checkbox>
                        <el-checkbox label="thursday" id="sch_check3"><label for="sch_check3">목</label></el-checkbox>
                        <el-checkbox label="friday" id="sch_check4"><label for="sch_check4">금</label></el-checkbox>
                        <el-checkbox label="saturday" id="sch_check5"><label for="sch_check5">토</label></el-checkbox>
                        <el-checkbox label="sunday" id="sch_check6"><label for="sch_check6">일</label></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <!-- 그룹소개 -->
                <el-form-item label="스터디 간단소개" prop="smallInfo">
                    <el-input type="textarea" v-model="groupDto.smallInfo" style="width: 85%;" :autosize="{ minRows: 1, maxRows: 3}" placeholder="스터디를 한마디로 표현한다면?"></el-input>
                </el-form-item>
                <el-form-item label="스터디 상세소개" prop="info">
                    <el-input type="textarea" v-model="groupDto.info" style="width: 85%;" :autosize="{ minRows: 10, maxRows: 20}" placeholder="스터디의 조건과 방법등 사람들이 보고 참여할 수 있게 소개 해주세요"></el-input>
                </el-form-item>
                <!-- 그룹 이미지 -->
                <el-form-item label="그룹대표 이미지" prop="image">
                    <el-upload
                        action=""
                        list-type="picture-card"
                        accept=".jpg, .jpeg, .png, .bmp"
                        multiple
                        :auto-upload="false"
                        :limit="1"
                        :on-change="handleChange"
                        :on-exceed="handleExceed"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="groupDto.dialogImageUrl" alt="">
                    </el-dialog>
                </el-form-item>
                <!-- 서브밋버튼 -->
                <div class="submitBtnDiv">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('groupDto')">Create</el-button>
                        <el-button @click="resetForm('groupDto')">Reset</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>

    </div>
</template>

<script>
import { loading } from 'element-ui';
export default {
    data(){
        return{
            loading: true,
            dialogVisible: false,
            loginMemberSeq: '',
            files: [],
            groupDto: {
                groupName: '',
                inter: '',
                address: '',
                extraAddress: '',
                maxMember:'',
                date: '',
                startDate: '',
                endDate: '',
                info: '',
                smallInfo: '',
                dialogImageUrl: '',
                groupSchedule: [],
            },
            rules: {
            groupName: [
                { required: true, message: '스터디 그룹 이름을 지어주세요', trigger: 'blur' },
                { min: 3, max: 20, message: '스터디 그룹명은 3글자에서 20자로 사이로 지어주세요', trigger: 'blur' }
            ],
            inter: [
                { required: true, message: '스터디 그룹 주제를 선택해주세요', trigger: 'change' }
            ],
            groupSchedule: [
                { type: 'array', required: true, message: '주간일정을 선택해주세요', trigger: 'change' }
            ],
            date: [
                { required: true, message: '시작일과 종료일을 선택해주세요', trigger: 'change' }
            ],
            info: [
                { required: true, message: '스터디 그룹을 소개해주세요', trigger: 'blur' },
                { min: 1, max: 500, message: '그룹소개는 500자 이내로 작성해주세요.', trigger: 'blur' }
            ],
            smallInfo:[
                { required: true, message: '스터디 그룹을 소개해주세요', trigger: 'blur' },
                { min: 1, max: 30, message: '간단소개는 30자 이내로 작성해주세요.', trigger: 'blur' }
            ],
            address:[
                { required: true, message: '지역을 선택해주세요', trigger: 'blur' },
            ]
            },
            pickerOptions: {
                shortcuts: [{
                    text: '일주일',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    end.setTime(start.getTime() + 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '한달',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    end.setTime(start.getTime() + 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '3개월',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    end.setTime(start.getTime() + 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '6개월',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    end.setTime(start.getTime() + 3600 * 1000 * 24 * 180);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '1년',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    end.setTime(start.getTime() + 3600 * 1000 * 24 * 365);
                    picker.$emit('pick', [start, end]);
                    }
                }]
            },
            InterListAll: ''
            }
    },
    methods:{
        gruopCreateApply: function (event) {
            alert("그룹신청이 완료되었습니다.\n심사 후 그룹개설이 완료됩니다.")
            this.$router.push({
                path: "/group"
            })
        },
        handleChange(file, fileList){
            this.files.push(file.raw) 
            console.log("addList")
            console.log(this.files)
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.groupDto.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleExceed(files, fileList){
            this.$message({
                showClose: true,
                center: true,
                message: '대표이미지는 1개만 업로드가능합니다.',
                type: 'error'
            })
        },
        dateToYYYYMMDD(date){
            var yyyy = date.getFullYear().toString();
            var mm = (date.getMonth() + 1).toString();
            var dd = date.getDate().toString();
            return  yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]);
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                let setGroupSchedule = new Object();
                setGroupSchedule.groupScheduleSeq = 0;
                setGroupSchedule.groupInfoSeq = 0;
                setGroupSchedule.sunday = 0;
                setGroupSchedule.monday = 0;
                setGroupSchedule.tuesday = 0;
                setGroupSchedule.wednesday = 0;
                setGroupSchedule.thursday = 0;
                setGroupSchedule.friday = 0;
                setGroupSchedule.saturday = 0;
                this.groupDto.groupSchedule.forEach(function(element) {
                    if(element === 'sunday') setGroupSchedule.sunday = 1;
                    if(element === 'monday') setGroupSchedule.monday = 1;
                    if(element === 'tuesday') setGroupSchedule.tuesday = 1;
                    if(element === 'wednesday') setGroupSchedule.wednesday = 1;
                    if(element === 'thursday') setGroupSchedule.thursday = 1;
                    if(element === 'friday') setGroupSchedule.thursday = 1;
                    if(element === 'saturday') setGroupSchedule.saturday = 1;
                })
                this.groupDto.groupSchedule = setGroupSchedule
                // 그룹스케쥴 세팅
                this.groupDto.startDate = this.dateToYYYYMMDD(this.groupDto.date[0]) 
                this.groupDto.endDate = this.dateToYYYYMMDD(this.groupDto.date[1])
                // 기간 세팅
                let formData = new FormData();
                formData.append('interSmallSeq', this.groupDto.inter);
                formData.append('groupName', this.groupDto.groupName);
                formData.append('groupLocation', this.groupDto.address);
                formData.append('info', this.groupDto.info);
                formData.append('smallInfo', this.groupDto.smallInfo);
                formData.append('maxMember', this.groupDto.maxMember);
                formData.append('image', this.groupDto.dialogImageUrl);
                formData.append('startDate', this.groupDto.startDate);
                formData.append('endDate', this.groupDto.endDate);
                formData.append('maxMember', this.groupDto.maxMember);
                // groupDto
                formData.append('sunday', this.groupDto.groupSchedule.sunday);
                formData.append('monday', this.groupDto.groupSchedule.monday);
                formData.append('tuesday', this.groupDto.groupSchedule.tuesday);
                formData.append('wednesday', this.groupDto.groupSchedule.wednesday);
                formData.append('thursday', this.groupDto.groupSchedule.thursday);
                formData.append('friday', this.groupDto.groupSchedule.friday);
                formData.append('saturday', this.groupDto.groupSchedule.saturday);
                // 로그인된 맴버Seq
                formData.append('memberSeq', this.loginMemberSeq);
                // 파일업로드
                formData.append('file', this.files[0]);

                axios.post("http://localhost:9000/creatGroupApply", formData,{
					headers:{'Content-Type' : 'multipart/form-data'}
                }).then(res => {
                                this.$router.push({name: "Group"})
                                alert(res.data + "그룹스터디 개설신청이 완료 되었습니다.\n개설여부는 마이페이지에서 확인 가능합니다.\n매주 월요일 9시에 승인여부가 업데이트 됩니다.")
                            })
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        execDaumPostcode() {
            new daum.Postcode({
                onComplete: (data) => {
                if (data.userSelectedType === 'R') {
                    this.groupDto.address = data.roadAddress;
                } else {
                    this.groupDto.address = data.jibunAddress;
                }
                if (data.userSelectedType === 'R') {
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    this.groupDto.extraAddress += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                    this.groupDto.extraAddress +=
                        this.groupDto.extraAddress !== ''
                        ? `, ${data.buildingName}`
                        : data.buildingName;
                    }
                    if (this.groupDto.extraAddress !== '') {
                    this.groupDto.extraAddress = ` (${this.groupDto.extraAddress})`;
                    }
                } else {
                    this.groupDto.extraAddress = '';
                }
                },
            }).open();
        }
    },
    mounted(){
        this.loginMemberSeq = this.$store.state.loginUser.memberSeq
        this.loading = true
        axios.get("http://localhost:9000/getInterListAll")
        .then(res => {
            this.InterListAll = res.data
            this.loading = false
        })
    }
}
</script>

<style scope>

.createContainer {
    position: relative;
    padding: 40px;
    margin: auto;
    overflow: hidden;
    width: 600px;
    height: auto;
    background: #f7f7f7;
}
.title{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size: 24px;
    margin: auto;
    margin-bottom: 20px;
    text-align: center;
}
.submitBtnDiv{
    text-align: center;
}
</style>