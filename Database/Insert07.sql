create table Question_Type(
Question_TypeId int not null identity primary key,
Name nvarchar(255),
Description nvarchar(255)
)

insert into Question_Type values ('SCQ','Single choice question')
insert into Question_Type values ('MCQ','Multiple choice question')


alter table Question add Id_Question_Type int not null default 1;
alter table Question add foreign key (Id_Question_Type) references Question_Type(Question_TypeId);