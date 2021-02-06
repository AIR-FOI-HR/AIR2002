alter table Quiz_User add Score int null
alter table Quiz add [Start_Date] DateTime not null
alter table Quiz add QuestionIds nvarchar(256)

alter table Quiz add Id_Category int not null
alter table Quiz add Foreign key (Id_Category) references Category(CategoryId)